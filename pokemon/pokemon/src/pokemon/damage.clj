;;;; damage.clj
;; Author: Vishesh Gupta

;;; KNOWN BUGS:
;; Water Spout/Eruption should be based on current HP
;; Items not implemented
;; Boosts not implemented
;; Status not implemented
;; Volatile Status not implemented j
;; Field variables not implemented (weather/terrain)
;; see special-bp for kinds of special moves not implemented
;; see abilities section for abilities that are being ignored for now
;;

(ns pokemon.damage
 "Damage Computation for pokemon battles"
 (:require [pokemon.constants :as pkc]))


(defn dmg [lvl atk df bp m]
  "Computes the final damage in number of hit points."
  (* m (+ 2 (* bp (/ (double atk) df) (/ (+ 10 (* 2 lvl)) 250)))))

(defn stat [lvl iv base ev nature]
  "Computes the stat value based on the pokemon's set (no boosts, abilities)"
  (* nature (+ 5 (* (/ lvl 100) (+ iv (* 2 base) (/ ev 4))))))

(defn hpstat [lvl iv base ev]
  "Computes the hp stat (different from all the other stats)"
  (+ lvl 10 (* (/ lvl 100) (+ iv (* 2 base) (/ ev 4)))))

(defn modifystat [stat boosts]
 "Modifies stats by boost level, in the range [-6 6]"
 (if (> boosts 0)
  (Math/floor (* stat (/ (+ 2 boosts) 2)))
  (Math/floor (* stat (/ 2 (- 2 boosts))))))


(defn typemod
  "Gives the type modifier for move with type `movetype` attacking a pokemon with
  type `pktype` or optionally 2 types"
  ([movetype pktype1 pktype2]`
    (* (typemod movetype pktype1) (typemod movetype pktype2)))
  ([movetype pktype]
   (get-in pkc/typechart [(.indexOf pkc/types movetype) (.indexOf pkc/types pktype)])))

(defn typemodv [movetype [pktype1 pktype2]]
 (if (nil? pktype2) (typemod movetype pktype1) (typemod movetype pktype1 pktype2)))

; this one is defined so that you can pass something
; like ["Dragon" ["Dragon" "Electric"]]
(defn stabmod [movetype pktypes]
 "(stabmod \"type\" [\"type1\" \"type2\"])
  Computes whether or not the attacker gets Same Type Attack Bonus for this move."
 (if (some #(= movetype %) pktypes) 1.5 1))

(defn stats [pkname [_ _ nature evs]]
 (let [[types basestats] (pkname pkc/pkmn)]
   (assoc (mapv #(int (stat 100 31 %1 %2 %3)) basestats evs (nature pkc/natures))
     0 (hpstat 100 31 (basestats 0) (evs 0)))))

; stats test
; (def test-set ["Life Orb" "Levitate" :Timid [4 0 0 252 0 252]])
; (stats :Keldeo test-set)


(defn special-bp
  "
  Works like middleware - if there's some special-bp case that this function
  understands it will recompute the bp, or else return the original bp.

  Special BP moves come in some variations, I've only implemented some...:

  Implemented:
  * Speed Based: Payback, Electro Ball, Gyro Ball
  * HP Based: Eruption, Water Spout, Flail, Reversal
  * Item Based: TODO implement itemmods in general

  Not Implemented:
  * Terrain Based: Nature Power (no one uses this move)
  * Boost Based: Stored Power, Punishment (boosts in general not implemented)
  * Weather Based: Weather Ball (usage on this is also sufficiently low...)
  * Weight Based: Low Kick, Grass Knot, Heavy Slam, Heat Crash (no weight data)
  * Status Based: Hex, Wake-up-Slap (Status is not implemented)"

  ([movename bp [atkhp _ _ _ _ atkspe] [defhp _ _ _ _ defspe]]
   (special-bp movename bp atkhp atkspe defhp defspe))
  ([movename bp atkhp atkspe defhp defspe]
   (case movename
     "Payback" (if (< atkspe defspe) (* 2 bp) bp)
     "Electro Ball" (let [r (Math/floor (/ atkspe defspe))]
                      (cond
                       (>= r 4) 150
                       (>= r 3) 120
                       (>= r 2) 80
                       :else 60))
     "Gyro Ball" (min 150 (Math/floor (* 25 (/ (double defspe) atkspe))))

     ; TODO these 4 should depend on current HP.
     ("Eruption" "Water Spout") 150
     ("Flail" "Reversal") 60
     bp)))


;;; Ability Based Modifiers

(defn ability-movetype-typemod [atkability movetype]
  "Should use to transform the type of the move based on the atkability
   usage `(let [type (ability-movetype atkability movetype)])`"
  (cond
   (and (= movetype "Normal") (= atkability "Aerilate")) "Flying"
   (and (= movetype "Normal") (= atkability "Pixelate")) "Fairy"
   (and (= movetype "Normal") (= atkability "Refrigerate")) "Ice"
   (= atkability "Normalize") "Normal"
   :else movetype))

(defn ability-movetype-bpmod [defability movetype]
  "Returns a function that should be applied to typemod to correct it.
   usage `((ability-typemod defability movetype) (typemod ...))`"
  (cond
   (= defability "Wonder Guard") #(if (< % 1) 0 %)
   ; immune cases
   (or
    (and (= defability "Sap Sipper") (= movetype "Grass"))
    (and (= defability "Flash Fire") (= movetype "Fire"))
    (and (contains? #{"Dry Skin", "Storm Drain", "Water Absorb"} defability)
         (= movetype "Water"))
    (and (contains? #{"Lightning Rod", "Lightningrod", "Motor Drive", "Volt Absorb"} defability)
         (= movetype "Electric"))
    (and (= defability "Levitate") (= movetype "Ground"))) #(identity 0))
  :else identity)

;;; Item Based Modifiers



;; Damage Calculations


(def nko
  "Given damage fraction (0.7 for example)
   returns how many turns it will take to KO"
  #(Math/ceil (/ 1.0 %)))

(defn damage
  "Given attacker/defender + sets and a move, compute damage in hit points"
  [[atkname [atkitem atkability atknature atkevs] :as attacker]
   movename
   [defname [defitem defability defnature defevs] :as defender]]
  (let [[atktype atkbase] (atkname pkc/pkmn)
        [dftype defbase] (defname pkc/pkmn)
        [movecat movetype movebp moveacc] (movename pkc/moves)
        atkstats (apply stats attacker)
        defstats (apply stats defender)
        m        (* (typemodv movetype dftype) (stabmod movetype atktype))]
    (int (case movecat
      "Physical" (dmg 100 (atkstats 1) (defstats 2) movebp m)
      "Special" (dmg 100 (atkstats 3) (defstats 4) movebp m)
      "Special-Physical" (dmg 100 (atkstats 3) (defstats 2) movebp m)
      -1)))) ; this shouldn't happen, but just in case

; damage test (should really have a tester class)
; (damage [:Keldeo ["Life Orb" "Justified" :Timid [0 0 0 252 4 252]]]
;         :Secret-Sword
;         [:Mega-Metagross ["Metagrossite" "Tough Claws" :Jolly [0 252 0 0 4 252]]])













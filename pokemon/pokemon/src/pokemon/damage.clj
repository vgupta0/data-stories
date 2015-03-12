;;;; damage.clj
;; Author: Vishesh Gupta

;;; KNOWN BUGS:
;; Water Spout/Eruption should be based on current HP
;; Items not implemented
;; Boosts not implemented
;; Status not implemented
;; Field variables not implemented (weather/terrain)
;; see special-bp for kinds of special moves not implemented
;; see abilities section for abilities that are being ignored for now
;;


(ns pokemon.damage
 "Damage Computation for pokemon battles"
 (:require 'pokemon.constants))


(defn dmg [lvl atk df bp m]
  "Computes the final damage in number of hit points."
  (* m (+ 2 (* bp (/ (double atk) df) (/ (+ 10 (* 2 lvl)) 250)))))

(defn stat [lvl iv base ev nature]
  "Computes the stat value based on the pokemon's set (no boosts, abilities)"
  (* nature (+ 5 (* (/ lvl 100) (+ iv (* 2 base) (/ ev 4))))))

(defn hpstat [lvl iv base ev]
  "Computes the hp stat (different from all the other stats)"
  (+ lvl 10 (* (/ lvl 100) (+ iv (* 2 base) (/ ev 4)))))


; A whole bunch of arities are supported to make life easy
(defn typemod
  "Gives the type modifier for move with type `movetype` attacking a pokemon with
  type `pktype`"
  ([movetype [:type [pktype1 pktype2]]] (typemod movetype pktype1 pktype2))
  ([movetype [pktype1 pktype2]] (typemod movetype pktype1 pktype2))
  ([movetype pktype1 pktype2]
   (* (typemod movetype pktype1) (typemod movetype pktype2)))
  ([movetype [:type pktype]] (typemod movetype pktype))
  ([movetype [pktype]] (typemod movetype pktype))
  ([movetype pktype]
   (get-in typechart [(.indexOf types movetype) (.indexOf types pktype)])))

; Again, a whole bunch of arities for ease of future development. 
(defn stabmod 
 "Computes whether or not the attacker gets Same Type Attack Bonus for this move."
 ([movetype [:type [pktype1 pktype2]]] (stabmod movetype pktype))
 ([movetype [:type [pktype]]] (stabmod movetype pktype))
 ([movetype [pktype]] (if (= movetype pktype) 1.5 1))
 ([movetype [pktype1 pktype2]]) (if (or (= movetype pktype1) (= movetype pktype2)) 1.5 1)))

(defn modifystats [stat boosts]
 "Modifies stats by boost level, in the range [-6 6]"
 (if (> boosts 0) 
  (Math/floor (* stat (/ (+ 2 boosts) 2)))
  (Math/floor (* stat (/ 2 (- 2 boosts))))))

(defn stats [pkname pkset]
 (let [pkdata (pkmn pkname)]))

(def test-set ["Life Orb" "Levitate" "Timid" [4 0 0 252 0 252] ["Draco Meteor", "Psyshock"]])


;;; Special BP moves come in some variations, I've only implemented some...:

;; Implemented
;; ------------
;; Speed Based: Payback, Electro Ball, Gyro Ball
;; HP Based: Eruption, Water Spout, Flail, Reversal
;;
;; Item Based: TODO(vishesh) implement itemmods in general

;; Not Implemented
;; ------------------
;; Terrain Based: Nature Power (no one uses this move)
;; Boost Based: Stored Power, Punishment (boosts in general not implemented)
;; Weather Based: Weather Ball (usage on this is also sufficiently low...)
;; Weight Based: Low Kick, Grass Knot, Heavy Slam, Heat Crash (no weight data)
;; Status Based: Hex, Wake-up-Slap (Status is not implemented)

(defn special-bp
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
     ; TODO these should depend on current HP.
     ("Eruption" "Water Spout") 150
     )))


;;; Ability Based Modifiers



;;; Item Based Modifiers









































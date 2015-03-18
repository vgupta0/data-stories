(ns pokemon.core
  (:require [pokemon.constants :as pkc])
  (:require [pokemon.damage :as pkd])
  (:require [clojure.java.shell :as shell :only [sh]])
  (:gen-class))

;; ---------------------------- JSON Conversions ------------------------------
; Full Pokemon Schema
;; [[:Latios
;;   ["Life Orb" :Levitate :Timid [0 0 0 252 4 252]]
;;   [:Psyshock :Draco-Meteor :Roost :Defog]]
;;  [0.72
;;   :burned
;;   [:confused :leech-seed]
;;   ; boosts - [atk def spa spd spe acc eva]
;;   [0 0 1 1 0 0 0]]]

;; EXAMPLE POKEMON OUTPUT
;; {"name":"Abomasnow",
;;  "type1":"Grass",
;;  "type2":"Ice",
;;  "level":100,
;;  "maxHP":323,
;;  "curHP":323,
;;  "HPEVs":8,
;;  "rawStats": {"at": 286, "df": 167, "sa": 283, "sd": 207, "sp": 177},
;;  "stats":{},
;;  "boosts": {"at": 0, "df": 0, "sa": 0, "sd": 0, "sp": 0},
;;  "evs": {"at": 190, "df": 0, "sa": 0, "sd": 0, "sp": 0},
;;  "nature":"Lonely",
;;  "ability":"Soundproof",
;;  "item":"",
;;  "status":"Healthy",
;;  "toxicCounter":0,
;;  "moves":[{"bp":40,
;;            "type":"Ice",
;;            "category":"Physical",
;;            "name":"Ice Shard",
;;            "isCrit":false,
;;            "hits":1}],
;;  "weight":135.5}

(defn movemap [move]
  (let [[rawcategory move-type bp acc] (move pkc/moves)
        category (if (= rawcategory "Special-Physical") "Special" rawcategory)
        physdamage (= rawcategory "Special-Physical")]
    {:bp bp :name move :type move-type
     :category category :dealsPhysicalDamage physdamage
     :isCrit false :hits 1}))

(def namestats
  #(into {} (mapv vector [:hp :at :df :sa :sd :sp] %)))

(defn pkmnmap
  ([pkmn-name
    [item ability nature
     [hpev atkev defev spaev spdev speev :as evs] :as pkmn-set]
    [movename :as movenames]]
   (pkmnmap pkmn-name pkmn-set movenames [1 :Healthy (vec (repeat 6 0))]))
  ([pkmn-name
    [item ability nature
     [hpev atkev defev spaev spdev speev :as evs] :as pkmn-set]
    [movename :as movenames]
    [curhp
     status
     volatile-status
     [boost :as boosts]]]

   (let
     [[types basestats] (pkc/pkmn pkmn-name)
      stats (pkd/stats pkmn-name pkmn-set)
      moves (mapv movemap (filter #(contains? pkc/moves %) movenames))
      ]
     {:name pkmn-name
      :type1    (first types)
      :type2    ((fnil identity "") (second types))
      :level    100
      :maxHP    (first stats)
      :curHP    (first stats)
      :HPEVs    (first evs)
      :rawStats (namestats stats)
      :stats    {}
      :boosts   (namestats boosts)
      :evs      (namestats evs)
      :nature   nature
      :ability  ability
      :item     item
      :status   status
      :toxicCounter 0
      :moves    moves
      :weight   100.0})))

(defn simple-json [o]
  (cond
   (string? o) (str "\"" o "\"")
   (keyword? o) (str "\"" (name o) "\"")
   (vector? o) (apply str
                (cons "[" (concat (interpose ", " (mapv simple-json o)) ["]"])))
   (map? o) (apply str
    (cons "{"
     (concat
      (interpose
       ", "
       (mapv (fn [[k v]]
               (str
                "\"" (if (keyword? k) (name k) k) "\""
                ": "
                (simple-json v)))
             (seq o)))
      '("}"))))
   :else (str o)))

;; Full Pokemon Schema
;; [pkmn-name [item ability nature [hpev atkev defev spaev spdev spdev]]
;;            [move1 move2? move3? move4?]
;;            [cur-hp status
;;             [volatile-status volatile-status?]
;;             [atkboost defboost spaboost spdboost speboost accboost evaboost]]]

(def pksets
  (with-open [rdr (clojure.java.io/reader "resources/ou_sets.clj")]
    (doall (line-seq rdr))))

(def testpk ((juxt second #(nth % 3) #(nth % 4)) (read-string (first pksets))))

(defn -main [& args]
  (println (:out (shell/sh "js" "../damage.js"
            (simple-json (apply pkmnmap testpk)))))
  (shutdown-agents))


















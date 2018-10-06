(ns clj-roguelike.action
    (:require [clj-roguelike.entity :refer [simplify-keyword]]
              [clj-roguelike.dungeon :refer [yx->m]]))

(declare tick-all)
(declare ticks-per-action)
(declare dispatch)

(defn effect-all [game]
  (update-in game
             [:entities]
             (fn [[player :as entities]]
                 (loop [ticks-until (ticks-per-action (:spd player))
                        entities entities]
                   (if (zero? ticks-until)
                     entities
                     (recur (dec ticks-until)
                            (tick-all (partial dispatch game) entities)))))))

(defn yx->entity
  "Returns the entity occupying the `yx` coordinate in `game`."
  [{:keys [area entities]} yx]
  (or (first (filter #(= yx (:yx %)) entities))
      ;; Tiles aren't really entities, so let's "entitize" them.
      {:type (:tile (yx->m yx area)) :yx yx}))

;; Temporary sketch of actions
; {:type :move}
; ;; rest and attack are really subset actions of move (walk would be
; ;; a more suitable blanket term) that are initiated depending on the
; ;; target coordinate. It may make sense to integrate them?
; {:type :rest}
; {:type :attack}
; {:type :use} ; using potions or equipping items

(defmulti dispatch
  (fn [game entity-id]
    (some-> game :entities (nth entity-id) :next-action :type)))

(declare encounter)

(defmethod dispatch :walk
  [{:keys [entities], :as game} entity-id]
  (let [{{:keys [dir]} :next-action, [y x] :yx, :as entity}
          (entities entity-id)
        next-yx (case (name dir)
                  "north" [(dec y) x]
                  "east"  [y (inc x)]
                  "south" [(inc y) x]
                  "west"  [y (dec x)])
        target-entity (yx->entity game next-yx)]
    (encounter entity target-entity)))

;; This is the default method for non-player `tickable-entities`. Doesn't apply
;; to `player` as the game-loop only runs when their :next-action is defined.
; (defmethod dispatch :default
;   )

(defmulti encounter
  (fn [entity-a entity-b]
    (mapv (comp keyword simplify-keyword :type)
          [entity-a entity-b])))

;; TODO once we start writing `encounter` methods for entities, we will need to
;; return both entities in a vector instead of just the player, and handle this
;; in our `tick` functions.

(defmethod encounter [:player :empty]
  [player {:keys [yx]}]
  (assoc player :yx yx))

(defmethod encounter [:player :wall]
  [player _]
  player)

;;;; Entity ticks for initiating actions/events

;;; All living entities have a `:ticks` key in their entity map, which is
;;; based on the entity's `:spd` value. The value of `:ticks` represents the
;;; remaining amount of ticks before the entity is allowed to initiate a new
;;; action.

(def ^:const base-speed
  "All speeds are relative to this value."
  10)

(def ^:const tickable-entities
  "Entity namespaces/simple keywords that `should-tick?`."
  #{"monster" "player"})

(defn- ticks-per-action
  "Computes the amount of ticks between each action for `spd`."
  [spd]
  (-> (* base-speed base-speed)
      (/ spd)
      double
      Math/round))

(defn- should-tick?
  "Returns whether the `entity` type allows ticking."
  [entity]
  (contains? tickable-entities (simplify-keyword (:type entity))))

(defn- reset-ticks
  "Sets the initial remaining ticks to an `entity` depending on its spd."
  [entity]
  (if (should-tick? entity)
    (assoc entity :ticks (ticks-per-action (:spd entity)))
    entity))

#_(defn reset-ticks-all
    "`reset-ticks` on a sequence of `entities`."
    [entities]
    (mapv reset-ticks entities))

(defn- has-ticks?
  "Returns true if `entity` has ticks key defined."
  [entity]
  (boolean (:ticks entity)))

(defn- tick
  "Initiates a single tick to `entity` and runs `dispatch-fn` if zero."
  [dispatch-fn entity]
  (if (should-tick? entity)
    (let [next-entity (update entity :ticks dec)]
      (if (and (zero? (:ticks next-entity))
               (zero? (:id next-entity)))
        ;; It's the player.
        (reset-ticks (dispatch-fn (:id entity)))
        next-entity))
    entity))

(defn tick-all
  "Safely `tick` all `entities`, resetting the key if not defined."
  [dispatch-fn entities]
  (let [partial-tick (partial tick dispatch-fn)
        apply-tick #(if (has-ticks? %)
                      (partial-tick %)
                      (partial-tick (reset-ticks %)))]
    (mapv apply-tick entities)))

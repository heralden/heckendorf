(ns clj-roguelike.util)

(def hotkeys
  "All numbers and lower/uppercase letters"
  (map (comp str char)
       (concat (range 48 58)
               (range 97 123)
               (range 65 91))))

(defn action [f]
  (fn [e]
    (.preventDefault e)
    (f)))

#?(:cljs (defn get-uid []
           (.getItem js/localStorage "uid")))

#?(:cljs (defn set-uid! [uid]
           (.setItem js/localStorage "uid" uid)))

(defmacro defstyled [fn-name bindings el stylem]
  `(defn ~fn-name [attrs# & rest#]
     (let [body# (cond (map? attrs#) rest#
                       (nil? rest#) (list attrs#)
                       :else (conj rest# attrs#))]
       (letfn [(~fn-name [{:keys ~bindings}] ~stylem)]
         (into [~el (merge {:class (if (map? attrs#)
                                     (herb.core/<class ~fn-name attrs#)
                                     (herb.core/<class ~fn-name))}
                           (when (map? attrs#)
                             (reduce dissoc attrs# (map keyword '~bindings))))]
           body#)))))

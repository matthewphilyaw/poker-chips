(ns poker-chips.chips)

(defn- build-chip-map "by denom" [chip-map]
  {:chip-map chip-map
   :buy-in (:buy-in chip-map)
   :chips (map #(hash-map :denom %2 :count %1)
               (take (count (:denoms chip-map))
                     (iterate (partial + (:step chip-map))
                              (:high-denom-count chip-map)))
               (reverse (:denoms chip-map)))})

(defn- build-totals [chip-map]
  (into chip-map
        {:total-chips (apply + (map :count 
                                    (:chips chip-map)))
         :total-points (apply + (map #(* (:denom %)
                                         (:count %)) 
                                     (:chips chip-map)))}))

(defn- build-point-value [chip-map]
  (into chip-map
        {:point-value (float (/ (:buy-in chip-map) 
                               (:total-points chip-map)))}))

(defn- build-chip-value [chip-map]
  (assoc-in chip-map
            [:chips]
            (map #(into %
                        {:value {:single (* (:denom %)
                                            (:point-value chip-map))
                                 :initial-count (* (:denom %)
                                                   (:point-value chip-map)
                                                   (:count %))}})
                 (:chips chip-map))))

(defn- drop-chipmap-key [chip-map]
  (dissoc chip-map :chip-map))

(defn process-chips [chip-map]
  (->
    (build-chip-map chip-map)
    build-totals
    build-point-value
    build-chip-value
    drop-chipmap-key))

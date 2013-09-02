(ns poker-chips.format) 

(defn pretty-print [chip-map] 
  (str "--------------------------------------------------------"
       "-------poker-chips\n"
       " General\n"
       "--------------------------------------------------------"
       "------------------\n"
       " buy in($):\t" (:buy-in chip-map) "\n"
       " point value($):\t" (:point-value chip-map) "\n\n"
       "--------------------------------------------------------"
       "------------------\n"
       " Totals\n"
       "--------------------------------------------------------"
       "------------------\n"
       " chips:\t" (:total-chips chip-map) "\n"
       " points:\t" (:total-points chip-map) "\n\n"
       "--------------------------------------------------------"
       "------------------\n"
       " break down by chip\n"
       "--------------------------------------------------------"
       "------------------\n"
       " denom\tcount\tvalue($)\ttotal-value($)\n"
       (apply str (flatten (map #(list " "
                                       (:denom %) "\t"
                                       (:count %) "\t"
                                       (format "%.3f" 
                                               (:single (:value %))) "\t"
                                       (format "%.3f"
                                               (:initial-count (:value %)))
                                       "\n")
                                (sort-by :denom (:chips chip-map))))) "\n\n"))


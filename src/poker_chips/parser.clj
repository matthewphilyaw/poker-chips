(ns poker-chips.parser)

(defn- find-colon [str]
  (re-find (re-matcher #":" str)))

(defn- split-on-space [line]
  (clojure.string/split line #" "))

(defn- convert-nums [line]
  (map #(or (and (not (find-colon %))
                 (read-string %)) 
            %)
       line))

(defn- build-initial-map [line]
  (zipmap [:buy-in :denoms :high-denom-count :step] line))

(defn- parse-denoms [init-map]
  (assoc-in init-map
            [:denoms]
            (sort (map #(read-string %)
                       (clojure.string/split (:denoms init-map) #":")))))

(defn parse-line [line]
  (-> 
    (split-on-space line)
    convert-nums
    build-initial-map
    parse-denoms))

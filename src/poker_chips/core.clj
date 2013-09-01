(ns poker-chips.core
  (:use [clojure.tools.cli :only (cli)]))

(defn -main [& args]
  (let [[opts args banner]
        (cli args
             ["-h" "--help" "show help" :flag true :default false]
             ["-bi" "--buy-in" "buy in amount" :flag false]
             ["-nhc" "--num-high-chip" "number of high chips" :flag false]
             ["-s" "--step" "step between chips" :flag false])]
    (or (and (or (:help opts) 
                 (not (and (:buy-in opts) 
                           (:num-high-chip opts)
                           (:step opts))))
             (do (println banner) true))
        (println opts))))

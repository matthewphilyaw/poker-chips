(ns poker-chips.core
  (:gen-class)
  (:use [clojure.tools.cli :only (cli)]
        poker-chips.parser
        poker-chips.chips
        poker-chips.format))

(defn -main [& args]
  (doseq [line (line-seq (java.io.BufferedReader. *in*))] 
    (printf (pretty-print (process-chips (parse-line line)))))
  (flush))

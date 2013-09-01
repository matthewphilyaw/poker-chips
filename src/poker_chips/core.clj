(ns poker-chips.core
  (:use [clojure.tools.cli :only (cli)]
        poker-chips.parser
        poker-chips.chips))

(defn -main [& args]
  (doseq [line (line-seq (java.io.BufferedReader. *in*))] 
    (prn (process-chips (parse-line line)))))

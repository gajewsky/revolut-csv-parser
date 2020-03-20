(ns revolut-csv-parser.core
  (:require [revolut-csv-parser.parser :as parser]))

(defn -main []
  (println (parser/csv->maps (slurp "revolut.csv"))))

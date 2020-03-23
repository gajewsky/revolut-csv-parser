(ns revolut-csv-parser.parser-test
  (:require [clojure.test :refer :all]
            [revolut-csv-parser.parser :refer :all :as parser]))

(deftest csv->map
  (testing "Converts csv string to key value map"
    (let [csv-string "Jan 3, 2020 ; Biedronka  ; 121.26 ;  ;  ;  ; 803.28; Groceries;\n"
          expected-map  {:date "Jan 2, 2020 "
                         :description " Biedronka  "
                         :paid-out " 121.26 "
                         :paid-in "  "
                         :exchange-out "  "
                         :exchange-in "  "
                         :balance " 803.28"
                         :category " Groceries"}]

      (is (parser/csv->maps csv-string) expected-map))))

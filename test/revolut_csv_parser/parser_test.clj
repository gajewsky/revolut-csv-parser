(ns revolut-csv-parser.parser-test
  (:require [clojure.test :refer :all]
            [revolut-csv-parser.parser :refer :all :as parser]))

(deftest csv->map
  (testing "Converts csv string to key value map"
    (let [csv-string "Jan 2, 2020 ; Biedronka  ; 121.26 ;  ;  ;  ; 803.28; Groceries;\n"
          expected-map  (list {:date "Jan 2, 2020"
                               :description "Biedronka"
                               :paid-out 121.26
                               :paid-in nil
                               :exchange-out nil
                               :exchange-in nil
                               :balance 803.28
                               :category "Groceries"})]

      (is (= expected-map (parser/csv->maps csv-string))))))

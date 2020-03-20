(ns revolut-csv-parser.parser
  (:require [clojure.string :as str]))

(def ^:private column-keys [:date
                            :description
                            :paid-out
                            :paid-in
                            :exchange-out
                            :exchange-in
                            :balance
                            :category
                            :notes])

(def ^:private conversions {:date identity
                            :description identity
                            :paid-out identity
                            :paid-in identity
                            :exchange-out identity
                            :exchange-in identity
                            :balance identity
                            :category identity
                            :notes identity})

(defn- parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(str/split % #";")
       (str/split string #"\n")))

(defn- normalize
  "Normalize each value basing on conversions instuctions"
  [column-key value]
  ((get conversions column-key) value))

(defn- str->maps
  "Returns a seq of maps like {:date \"Jan 2, 2020\" description \"Biedronka\"}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [key value]]
                   (assoc row-map key (normalize key value)))
                 {}
                 (map vector column-keys unmapped-row)))
       rows))

(defn csv->maps
  [csv-string]
  (str->maps (parse csv-string)))

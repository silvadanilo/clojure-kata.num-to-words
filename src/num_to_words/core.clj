(ns num-to-words.core
  (:gen-class))

(def symbols {0 "zero"
              1 "one"
              2 "two"
              3 "three"
              4 "four"
              5 "five"
              6 "six"
              7 "seven"
              8 "heigth"
              9 "nine"
              10 "ten"
              11 "ileven"
              12 "twelve"
              13 "thirdteen"
              14 "fourteen"
              15 "fifteen"
              16 "sixteen"
              17 "seventeen"
              18 "eighteen"
              19 "nineteen"
              20 "twenty"
              30 "thirty"
              40 "forty"
              50 "fifty"
              100 "hundred"})

(defn exp [x n]
  (reduce * (repeat n x)))

(defn find-max-divisor
  ([number base] (find-max-divisor number base 1))
  ([number base position]
   (if (> (/ number (exp base position)) 1)
     (find-max-divisor number base (+ position 1))
     (exp base (- position 1)))))

(defn decompose [number]
  (let [max-divisor (find-max-divisor number 10)]
    (loop [max-divisor max-divisor
           number number
           acc ()]
      (if (= max-divisor 0)
        acc
        (recur
          (quot max-divisor 10)
          (mod number max-divisor)
          (concat
            acc
            (list (* (quot number max-divisor) max-divisor))))))))

(defn spell-out [number]
  (if (some #{number} (keys symbols))
    (get symbols number)
    (clojure.string/join " " (map spell-out (decompose number)))
  ))


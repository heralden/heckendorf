(ns heckendorf.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [heckendorf.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))

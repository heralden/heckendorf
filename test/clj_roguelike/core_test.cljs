(ns clj-roguelike.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [clj-roguelike.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))

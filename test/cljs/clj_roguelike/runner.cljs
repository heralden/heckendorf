(ns clj-roguelike.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [clj-roguelike.core-test]))

(doo-tests 'clj-roguelike.core-test)

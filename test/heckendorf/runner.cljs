(ns heckendorf.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [heckendorf.core-test]))

(doo-tests 'heckendorf.core-test)

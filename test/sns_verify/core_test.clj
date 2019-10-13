(ns sns-verify.core-test
  (:require
   [clojure.test :refer :all]
   [sns-verify.core :refer :all]))

(testing "get-handler"
  (deftest get-success
    (is (= (handler {:request-method :get})
           {:status 200
            :headers {"Content-Type" "text/html"}
            :body "Hello World"}))))

(testing "post-handler"
  (deftest post-success
    (is (= (handler {:request-method :post})
           {:status 200
            :headers {"Content-Type" "text/html"}
            :body "Hello World"}))))
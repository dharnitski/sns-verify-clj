(ns sns-verify.core-test
  (:require
   [clojure.test :refer :all]
   [clojure.java.io :as io]
   [sns-verify.core :refer :all]))

(testing "get-handler"
  (deftest get-success
    (is (= (handler {:request-method :get})
           {:status 200
            :headers {"Content-Type" "text/html"}
            :body "Hello World"}))))

(testing "sns-handler"
  (deftest sns-success
    (is (= (handler {:request-method :post
                     :body (io/input-stream "testdata/sns-valid.json")})
           {:status 200
            :headers {"Content-Type" "text/html"}
            :body "arn:aws:sns:us-east-1:941749041526:test"})))

  (deftest sns-not-valid
    (is (thrown? com.amazonaws.SdkClientException
                 (handler {:request-method :post
                           :body (io/input-stream "testdata/sns-tampered.json")})))))


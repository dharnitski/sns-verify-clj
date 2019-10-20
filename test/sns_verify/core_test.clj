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
            :body {:topic-arn "arn:aws:sns:us-east-1:941749041526:test"
                   :message-id "a5b3def0-7b01-5646-87fd-88eda0a28ee7"
                   :timestamp #inst "2019-10-14T02:11:47.508-00:00"}})))

  (deftest sns-not-valid
    (is (thrown? com.amazonaws.SdkClientException
                 (handler {:request-method :post
                           :body (io/input-stream "testdata/sns-tampered.json")})))))


(ns sns-verify.core
  (:import (com.amazonaws.services.sns.message SnsMessageManager)))

(defn get-handler []
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World"})

(def manager (new SnsMessageManager "us-east-1"))

(defn parse-message
  "Loads message from request body stream. Throws if message is not valid."
  [body-stream]
  (let [message (.parseMessage manager body-stream)]
    {:topic-arn (.getTopicArn message)
     :message-id (.getMessageId message)
     :timestamp (.getTimestamp message)}))

(defn sns-handler
  "Returns 200 and Parsed message fields for valid SNS Message."
  [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (parse-message (:body request))})

(defn handler [request]
  (let [method (:request-method request)]
    (case method
      :get (get-handler)
      :post (sns-handler request)
      {:status 404
       :headers {"Content-Type" "text/html"}
       :body (str "Not supported verb " method)})))
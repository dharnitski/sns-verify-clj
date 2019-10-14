(ns sns-verify.core
  (:import (com.amazonaws.services.sns.message SnsMessageManager)))

(defn get-handler []
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World"})

(def manager (new SnsMessageManager "us-east-1"))

(defn parse-message
  "Loads message from requiest body stream. Throw if message is not valid."
  [body-stream]
  (let [message (.parseMessage manager body-stream)]
    {:topic-arn (.getTopicArn message)}))

(defn sns-handler
  "Returns 200 and TopicARN for valid SNS Message."
  [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (:topic-arn (parse-message (:body request)))})

(defn handler [request]
  (let [method (:request-method request)]
    (case method
      :get (get-handler)
      :post (sns-handler request)
      {:status 404
       :headers {"Content-Type" "text/html"}
       :body (str "Not supported verb " method)})))
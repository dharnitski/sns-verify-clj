(ns sns-verify.core)

(defn get-handler []
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World"})

(defn post-handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World"})

(defn handler [request]
  (case :request-method request
        :get (get-handler)
        :post (post-handler request)))
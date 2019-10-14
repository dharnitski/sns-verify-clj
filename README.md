# SNS HTTP(S) Message verification Clojure Sample

[![CircleCI](https://circleci.com/gh/dharnitski/sns-verify-clj.svg?style=svg)](https://circleci.com/gh/dharnitski/sns-verify-clj)

## Prerequisites

* [Leiningen](https://github.com/technomancy/leiningen) 2.9.1+

## Prepare

    lein deps

## Start a web server

    lein ring server

## Intergation Test

Requires running web server.

    curl -X POST -H "Content-Type: application/json" -d @testdata/sns-valid.json  http://localhost:8080

## Run tests

    lein test

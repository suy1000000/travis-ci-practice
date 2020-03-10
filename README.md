## Homework

* Fork this repo
	* git clone --recurse-submodules git@github.com:your github name/your repository name.git
	* Modify .travis.yml
* 加上 DB 層 （DynamoDB）
* Create VPC Endpoint for（DynamoDB）

![](https://i.imgur.com/Kt5yg1k.png)

### Create DynamoDB

* Table Name : User
* Partition key : String id

### Use AMIs

104-aws-training-2020-01-17

---

# Project Intro

## Framework

* [Jersey](https://jersey.github.io/)
* [Jersey Spring3](https://mvnrepository.com/artifact/org.glassfish.jersey.ext/jersey-spring3)
* [Swagger](https://swagger.io/)
* [rest-api-base](https://github.com/VilleBez/rest-api-base)

## Local Start

run gradle appRun command, then it will start a tomcat8 container. 

```sh
$ gradle appRun
```

## Document

* Resource WADL：http://localhost:8080/example/application.wadl
* Swagger Specification：
	* JSON：http://localhost:8080/example/swagger.json
	* YAML：http://localhost:8080/example/swagger.yaml

Try it, use [Swagger UI Console](https://chrome.google.com/webstore/detail/swagger-ui-console/nffpgbcbofmiohjiainnccpelgfdnioo) chrome extension.
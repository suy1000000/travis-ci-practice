## Introduction
Use Jersey + Spring + Swagger + rest-api-base Framework Implement User Rest API Example.  

Follow [API Guideline](https://github.com/104corp/guideline-draft/blob/master/implement/api.md).

## Framework

* [Jersey](https://jersey.github.io/)
* [Jersey Spring3](https://mvnrepository.com/artifact/org.glassfish.jersey.ext/jersey-spring3)
* [Swagger](https://swagger.io/)
* [rest-api-base](https://github.com/VilleBez/rest-api-base)

## Start

run gradle appRun command, then it will start a tomcat8 container. 

```sh
gradle appRun
```

## Document

* Resource WADL：http://localhost:8080/jersey_spring_swagger/application.wadl
* Swagger Specification：
	* JSON：http://localhost:8080/jersey_spring_swagger/swagger.json
	* YAML：http://localhost:8080/jersey_spring_swagger/swagger.yaml

Try it, on [Swagger UI](http://petstore.swagger.io/?url=http://localhost:8080/jersey_spring_swagger/swagger.json).


## User Rest API Example

* 取得使用者列表；GET http://localhost:8080/jersey_spring_swagger/users/
* 新增使用者；POST http://localhost:8080/jersey_spring_swagger/users/
* 取得某使用者；GET http://localhost:8080/jersey_spring_swagger/users/{id}
* 修改某使用者；PUT http://localhost:8080/jersey_spring_swagger/users/{id}
* 刪除使用者；DELETE http://localhost:8080/jersey_spring_swagger/users/{id} 
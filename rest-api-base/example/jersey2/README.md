## Introduction
Use Jersey + rest-api-base Framework Implement User Rest API Example.  



## Framework

* [Jersey](https://jersey.github.io/)
* [rest-api-base](https://github.com/VilleBez/rest-api-base)

## Start

run gradle appRun command, then it will start a tomcat8 container. 

```sh
gradle appRun
```

## Try Resource Wadl

http://localhost:8080/jersey2/application.wadl

## Try User Rest API Example

* 取得使用者列表；GET http://localhost:8080/jersey2/users/
* 新增使用者；POST http://localhost:8080/jersey2/users/
* 取得某使用者；GET http://localhost:8080/jersey2/users/{id}
* 修改某使用者；PUT http://localhost:8080/jersey2/users/{id}
* 刪除使用者；DELETE http://localhost:8080/jersey2/users/{id}

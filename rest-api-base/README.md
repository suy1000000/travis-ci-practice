## Introduction
REST API Common Framework base on JAX-RS.  

Provide the following functions:  

* Exception Mapping (Error Handle, include HTTP Status 400、500)
* XSS Filter, Utils, JsonDeserialize
* Request, Response wrapper Filter


## Framework

* [JAX-RS](https://zh.wikipedia.org/wiki/JAX-RS): Java API for RESTful Web Services
* [ESAPI](https://www.owasp.org/index.php/Category:OWASP_Enterprise_Security_API): [OWASP](https://www.owasp.org/index.php/Main_Page) Enterprise Security API
* [Swagger-Annotations](https://github.com/swagger-api/swagger-core/wiki/Annotations)

## Usage

jersey example

	```
	<servlet>
		<servlet-name>Jersey</servlet-name>
		<servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
		<init-param>
			<param-name>jersey.config.server.provider.packages</param-name>
			<param-value>
				idv.villebez.exception.mapper,
				idv.villebez.filter
        	</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>
	```
	
## XSS

### Jackson JsonDeserialize Annotation
```
@JsonDeserialize(using = XSSDeserializer.class)
```

### Utils
```
value = XSSUtils.stripSpecificXSS(value);
value = XSSUtils.stripXSS(value);
```

	
## Example Project

* [JAX-RS](example/JAX-RS/)
* [Jersey](example/jersey2/)
* [Jersey + Spring + Swagger](example/jersey_spring_swagger/)

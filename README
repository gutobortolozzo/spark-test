Tiny Java layer to request spark services only using Java 8 SE.

This layer is intended to support integration tests of spark (https://github.com/perwendel/spark).


```java

UrlResponse response = SparkRequest.prepare().toPath("/create/john/doe").usingMethod(HttpMethod.GET).submit();

response.status();
> 200
response.headers();
> {null=[HTTP/1.1 200 OK], Server=[Jetty(9.0.2.v20130417)], Content-Length=[27], Content-Type=[text/html; charset=UTF-8]}
response.body();
> {"name":"john","cpf":"doe"}
```

Example using gson to transform json response into java objects

```java
Person person = prepare().toPath("/create/john/doe").usingMethod(HttpMethod.GET).submit((response) -> {
	Type type = new TypeToken<Person>(){}.getType();
	return new Gson().fromJson(response, type);
});

person.toString();
> Person [name=john, cpf=doe]

```

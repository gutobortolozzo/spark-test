package com.spark.services;

import static spark.Spark.get;
import static spark.Spark.post;

import com.spark.rules.Person;

public class SparkBoot {

	public static void start() {

		get("/ping", (request, response) -> {
			return "pong";
		});
		
		post("/greeting", (request, response) -> {
			return "Greetings "+request.body();
		});
		
		post("/name", (request, response) -> {
			return new Person(request.body());
		}, new JsonTransformer());
		
		get("/create/:name", (request, response) -> {
			return new Person(request.params(":name"));
		}, new JsonTransformer());
	}

}

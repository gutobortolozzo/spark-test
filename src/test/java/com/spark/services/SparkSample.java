package com.spark.services;

import static spark.Spark.get;
import static spark.Spark.post;

import com.spark.rules.Person;

public class SparkSample {

	public static void start() {

		get("/ping", (request, response) -> {
			return "pong";
		});
		
		post("/greeting", (request, response) -> {
			return "Greetings "+request.body();
		});
		
		post("/name", (request, response) -> {
			String name = request.body().split("&")[0].split("=")[1];
			String lastname = request.body().split("&")[1].split("=")[1];
			return new Person(name, lastname);
		}, new JsonTransformer());
		
		get("/create/:name/:lastname", (request, response) -> {
			return new Person(request.params(":name"), request.params(":lastname"));
		}, new JsonTransformer());
	}

}

package com.spark.services;

import static spark.Spark.get;

import com.spark.rules.Person;

public class SparkSample {

	public static void start() {

		get("/ping", (request, response) -> {
			return "pong";
		});
		
		get("/create/:name/:lastname", (request, response) -> {
			return new Person(request.params(":name"), request.params(":lastname"));
		}, new JsonTransformer());
	}

}

package com.spark.services;

import spark.Spark;

import com.rules.Person;

public class SparkSample {

	public static void start() {

		Spark.before((request, response) -> {
			System.out.println(request.pathInfo());
		});

		Spark.get("/ping/:name", (request, response) -> {
			return new Person(request.params(":name"), "Bull");
		}, new JsonTransformer());
	}

}

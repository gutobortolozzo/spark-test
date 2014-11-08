package com.spark.tests;

import static com.spark.Request.prepare;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Type;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spark.HttpMethod;
import com.spark.rules.Person;

public class SparkServiceWithBodyRequestTransformedTest extends BaseTest{

	private Person subject;

	@Test public void requestWithPerson() {
		assertEquals("Person [name=John, lastname=Bull]", subject.toString());
	}
	
	@Before public void before(){
		subject = prepare().toPath("/name").usingMethod(HttpMethod.POST).submit("name=John&lastname=Bull", (response) -> {
			Type type = new TypeToken<Person>(){}.getType();
			return new Gson().fromJson(response, type);
		 });
	}

}

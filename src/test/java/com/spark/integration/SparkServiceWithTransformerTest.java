package com.spark.integration;

import static com.spark.Request.prepare;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Type;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spark.HttpMethod;
import com.spark.rules.Person;

public class SparkServiceWithTransformerTest extends BaseTest{
	
	private Person subject;	
	
	@Test public void person() {
		assertEquals("Person [name=john]", subject.toString());
	}
	
	@Before public void before(){
		subject = prepare().toPath("/create/john").usingMethod(HttpMethod.GET).submit((response) -> {
			Type type = new TypeToken<Person>(){}.getType();
			return new Gson().fromJson(response, type);
		 });
	}
}

package com.spark.integration;

import static com.spark.Request.prepare;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.spark.HttpMethod;
import com.spark.Response;

public class SparkServiceWithBodyRequestTest extends BaseTest{

	private Response subject;

	@Test public void requestWithBody() {
		assertEquals("Greetings John", subject.body());
	}
	
	@Test public void requestWithBodyHeaders() {
		assertEquals("[text/html; charset=UTF-8]", subject.headers().get("Content-Type").toString());
	}
	
	@Before public void before(){
		subject = prepare().toPath("/greeting").usingMethod(HttpMethod.POST).submit("John");
	}
}

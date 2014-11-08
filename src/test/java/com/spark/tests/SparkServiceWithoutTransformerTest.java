package com.spark.tests;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.spark.HttpMethod;
import com.spark.Request;
import com.spark.Response;

public class SparkServiceWithoutTransformerTest extends BaseTest {

	private Response subject;	
	
	@Test public void status() {
		assertEquals(200, subject.status());
	}
	
	@Test public void header() {
		assertEquals("[text/html; charset=UTF-8]", subject.headers().get("Content-Type").toString());
	}
	
	@Test public void body() {
		assertEquals("{\"name\":\"john\",\"lastname\":\"doe\"}", subject.body());
	}
	
	@Before public void before(){
		subject = Request.prepare().toPath("/create/john/doe").usingMethod(HttpMethod.GET).submit();
	}
}

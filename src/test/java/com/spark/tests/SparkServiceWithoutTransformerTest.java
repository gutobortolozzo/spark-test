package com.spark.tests;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.spark.HttpMethod;
import com.spark.SparkRequest;
import com.spark.UrlResponse;

public class SparkServiceWithoutTransformerTest extends BaseTest {

	private UrlResponse subject;	
	
	@Test public void status() {
		assertEquals(200, subject.status());
	}
	
	@Test public void header() {
		assertEquals("[text/html; charset=UTF-8]", subject.headers().get("Content-Type").toString());
	}
	
	@Test public void body() {
		assertEquals("{\"name\":\"john\",\"cpf\":\"doe\"}", subject.body());
	}
	
	@Before public void before(){
		subject = SparkRequest.prepare().toPath("/create/john/doe").usingMethod(HttpMethod.GET).submit();
	}
}

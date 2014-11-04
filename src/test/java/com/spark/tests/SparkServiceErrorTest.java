package com.spark.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.spark.HttpMethod;
import com.spark.SparkRequest;
import com.spark.UrlResponse;

public class SparkServiceErrorTest extends BaseTest{

	@Test public void invalidStatusUrl() {
		UrlResponse subject = SparkRequest.prepare().toPath("/create/john").usingMethod(HttpMethod.GET).submit();
		assertEquals(404, subject.status());
	}
	
	@Test public void invalidHeaderUrl() {
		UrlResponse subject = SparkRequest.prepare().toPath("/create/john").usingMethod(HttpMethod.GET).submit();
		assertEquals("[HTTP/1.1 404 Not Found]", subject.headers().get(null).toString());
	}
	
	@Test public void invalidBodyUrl() {
		UrlResponse subject = SparkRequest.prepare().toPath("/create/john").usingMethod(HttpMethod.GET).submit();
		assertTrue(subject.body().contains("The requested route [/create/john] has not been mapped in Spark"));
	}
}

package com.spark.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.spark.HttpMethod;
import com.spark.Request;
import com.spark.Response;

public class SparkServiceErrorTest extends BaseTest{

	@Test public void invalidStatusUrl() {
		Response subject = Request.prepare().toPath("/create/john").usingMethod(HttpMethod.GET).submit();
		assertEquals(404, subject.status());
	}
	
	@Test public void invalidHeaderUrl() {
		Response subject = Request.prepare().toPath("/create/john").usingMethod(HttpMethod.GET).submit();
		assertEquals("[HTTP/1.1 404 Not Found]", subject.headers().get(null).toString());
	}
	
	@Test public void invalidBodyUrl() {
		Response subject = Request.prepare().toPath("/create/john").usingMethod(HttpMethod.GET).submit();
		assertTrue(subject.body().contains("The requested route [/create/john] has not been mapped in Spark"));
	}
	
	@Test public void defaultMachineCharsetUrl() {
		Response subject = Request.prepare().toPath("/create/john").usingMethod(HttpMethod.GET).submit();
		assertTrue(subject.headers().get("Content-Type").toString().contains("UTF-8"));
	}
}

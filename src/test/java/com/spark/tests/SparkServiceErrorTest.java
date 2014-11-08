package com.spark.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.spark.HttpMethod;
import com.spark.Request;
import com.spark.Response;

public class SparkServiceErrorTest extends BaseTest{

	public ExpectedException rule = ExpectedException.none();
	
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
	
	@Test(expected = RuntimeException.class) public void requestWithNullBody() {
		rule.expectMessage("Non null body required");
		rule.expect(RuntimeException.class);
		Request.prepare().toPath("/name").usingMethod(HttpMethod.GET).submit((String)null);
	}
}

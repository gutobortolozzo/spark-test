package com.spark.tests;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import spark.Spark;

import com.spark.HttpMethod;
import com.spark.SparkRequest;
import com.spark.UrlResponse;
import com.spark.services.SparkSample;

public abstract class BaseTest {
	
	@Test public void getRequestStatusHeaders() {
		UrlResponse response = SparkRequest.prepare().toPath("/ping").usingMethod(HttpMethod.GET).submit();
		assertEquals("[Jetty(9.0.2.v20130417)]", response.headers().get("Server").toString());
		assertEquals(200, response.status());
		assertEquals("pong", response.body());
	}
	
	@BeforeClass public static void start() throws Exception{
		SparkSample.start();
		Thread.sleep(200);
	}
	
	@AfterClass public static void stop() throws Exception{
		Spark.stop();
	}
}

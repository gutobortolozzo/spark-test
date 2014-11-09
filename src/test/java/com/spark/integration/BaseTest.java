package com.spark.integration;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import spark.Spark;

import com.spark.HttpMethod;
import com.spark.Request;
import com.spark.Response;
import com.spark.services.SparkBoot;

public abstract class BaseTest {
	
	@Test public void getRequestStatusHeaders() {
		Response response = Request.prepare().toPath("/ping").usingMethod(HttpMethod.GET).submit();
		assertEquals("[Jetty(9.0.2.v20130417)]", response.headers().get("Server").toString());
		assertEquals(200, response.status());
		assertEquals("pong", response.body());
	}
	
	@BeforeClass public static void start() throws Exception{
		SparkBoot.start();
		Thread.sleep(200);
	}
	
	@AfterClass public static void stop() throws Exception{
		Spark.stop();
	}
}

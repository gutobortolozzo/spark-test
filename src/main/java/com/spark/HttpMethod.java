package com.spark;

public enum HttpMethod {
	
	GET("GET"),
	POST("POST"),
	PUT("PUT"),
	DELETE("DELETE");
	
	private String name;

	private HttpMethod(String name){
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}

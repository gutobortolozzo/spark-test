package com.spark;

import java.util.List;
import java.util.Map;

public class UrlResponse {
	
	private Map<String, List<String>> headers;
	private String body;
	private int status;
	
	UrlResponse(Map<String, List<String>> headers, String body, int status) {
		this.headers = headers;
		this.body = body;
		this.status = status;
	}

	public Map<String, List<String>> headers() {
		return headers;
	}

	public String body() {
		return body;
	}

	public int status() {
		return status;
	}
}

package com.spark;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class TestUtils {
	
	static UrlResponse doMethod(String requestMethod, String path, String body){
		try {
			return getResponse(requestMethod, path);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private static UrlResponse getResponse(String requestMethod, String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(requestMethod);
		connection.connect();
		
		InputStream stream = connection.getResponseCode() == 200 ? 
								connection.getInputStream() : 
								connection.getErrorStream();
		
		String response = readStream(stream);
		
		return new UrlResponse(connection.getHeaderFields(), response, connection.getResponseCode());
	}
	
	private static String readStream(InputStream stream) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		while(br.ready()) sb.append(br.readLine());
		
		return sb.toString();
	}
}

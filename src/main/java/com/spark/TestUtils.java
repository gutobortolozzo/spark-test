package com.spark;

import static java.nio.charset.Charset.defaultCharset;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class TestUtils {
	
	static Response doMethod(String requestMethod, String path, String body){
		try {
			return getResponse(requestMethod, path);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private static Response getResponse(String requestMethod, String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(requestMethod);
		connection.connect();
		
		InputStream stream = connection.getResponseCode() == 200 ? 
								connection.getInputStream() : 
								connection.getErrorStream();
		
		String response = readStream(stream);
		
		return new Response(connection.getHeaderFields(), response, connection.getResponseCode());
	}
	
	private static String readStream(InputStream stream) throws Exception {
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream, defaultCharset()));
		
		String line = "";
		while((line = reader.readLine()) != null) builder.append(line);
		
		return builder.toString();
	}
}

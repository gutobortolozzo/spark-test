package com.spark;

import static java.nio.charset.Charset.defaultCharset;
import static java.util.Objects.requireNonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class TestUtils {
	
	static Response doMethod(String requestMethod, String path, String body){
		try {
			requireNonNull(body, "Non null body required");
			return getResponse(requestMethod, path, body);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private static Response getResponse(String requestMethod, String path, String body) throws Exception {
		URL url = new URL(path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(requestMethod);
		doOutputIfPresent(body, connection);
		connection.connect();
		
		InputStream stream = connection.getResponseCode() == 200 ? 
								connection.getInputStream() : 
								connection.getErrorStream();
		
		String response = readStream(stream);
		
		return new Response(connection.getHeaderFields(), response, connection.getResponseCode());
	}

	private static void doOutputIfPresent(String body, HttpURLConnection connection) throws IOException {
		if(body.isEmpty()) return;
		connection.setDoOutput(true);
		OutputStream outputStream = connection.getOutputStream();
		outputStream.write(body.getBytes(defaultCharset()));
		outputStream.flush();
		outputStream.close();
	}
	
	private static String readStream(InputStream stream) throws Exception {
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream, defaultCharset()));
		
		String line = "";
		while((line = reader.readLine()) != null) builder.append(line);
		
		return builder.toString();
	}
}

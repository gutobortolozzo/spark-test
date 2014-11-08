package com.spark;

public class Request {
	
	private String url = "http://localhost";
	private int port = 4567;
	private HttpMethod method = HttpMethod.GET;
	private String path = "";
	
	public static Request prepare(){
		Request request = new Request();
		return request;
	}
	
	public static Request build(String url, int port){
		Request request = new Request();
		request.url = url;
		request.port = port;
		return request;
	}
	
	public Request toPath(String path){
		this.path = path;
		return this;
	}
	
	public Request usingMethod(HttpMethod method){
		this.method = method;
		return this;
	}
	
	public <Type> Type submit(Transformer<Type> transformer){
		String destinyUrl = getHostUrl();
		Response response = TestUtils.doMethod(method.toString(), destinyUrl, null);
		return transformer.from(response.body());
	}
	
	public Response submit(){
		String destinyUrl = getHostUrl();
		return TestUtils.doMethod(method.toString(), destinyUrl, null);
	}
	
	private String getHostUrl() {
		return url+":"+port+path;
	}
}

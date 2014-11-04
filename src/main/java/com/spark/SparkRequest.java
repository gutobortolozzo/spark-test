package com.spark;

public class SparkRequest {
	
	private String url = "http://localhost:";
	private int port = 4567;
	private HttpMethod method = HttpMethod.GET;
	private String path = "";
	
	public static SparkRequest prepare(){
		SparkRequest request = new SparkRequest();
		return request;
	}
	
	public static SparkRequest build(String url, int port){
		SparkRequest request = new SparkRequest();
		request.url = url;
		request.port = port;
		return request;
	}
	
	public SparkRequest toPath(String path){
		this.path = path;
		return this;
	}
	
	public SparkRequest usingMethod(HttpMethod method){
		this.method = method;
		return this;
	}
	
	public <Type> Type submit(Transformer<Type> transformer){
		String destinyUrl = url+port+path;
		UrlResponse response = TestUtils.doMethod(method.toString(), destinyUrl, null);
		return transformer.from(response.body());
	}
	
	public UrlResponse submit(){
		String destinyUrl = url+port+path;
		return TestUtils.doMethod(method.toString(), destinyUrl, null);
	}
}

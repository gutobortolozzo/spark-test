package com.spark;

public interface Transformer<T> {
	
	public T from(String response);
}

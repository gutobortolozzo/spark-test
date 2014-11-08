package com.spark.rules;

public class Person {
	
	private String name;
	private String lastname;
	
	public Person(String name, String lastname) {
		this.name = name;
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", lastname=" + lastname + "]";
	}
}

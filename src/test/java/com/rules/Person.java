package com.rules;

public class Person {
	
	private String name;
	private String cpf;
	
	public Person(String name, String cpf) {
		this.name = name;
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", cpf=" + cpf + "]";
	}
}

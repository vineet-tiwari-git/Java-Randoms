package com.vineet.designpatterns.creational;

public class SingletonPatternExample {
	
	private static SingletonPatternExample instance;

	// Private constructor to prevent instantiation
	private SingletonPatternExample() {
	}

	// Public method to provide access to the instance
	public static synchronized SingletonPatternExample getInstance() {
		if (instance == null) {
			instance = new SingletonPatternExample();
		}
		return instance;
	}

	public static void main(String[] args) {
		SingletonPatternExample singleton = SingletonPatternExample.getInstance();
		SingletonPatternExample singleton2 = SingletonPatternExample.getInstance();
		
		System.out.println("Instance == comparison: " + Boolean.valueOf( singleton == singleton2));
		System.out.println("Instance equals comparison: " + Boolean.valueOf( singleton.equals(singleton2)));
	}

}

package com.vineet.java8;

import java.util.Arrays;
import java.util.List;

public class FunctionalInterfaceTest {

	public static String capitalizeString(String s, MyFunctionalInterface functionaInterface) {
		return functionaInterface.capitalizeString(s);
	}

	public static void main(String args[]) {
		String s =" String to be converted to block letters";
		System.out.println(capitalizeString(s, a -> a.toUpperCase()));
		
		List<String> names = Arrays.asList("bob", "josh", "megan");

		names.replaceAll(name -> name.toUpperCase());
	}
}

@FunctionalInterface
interface MyFunctionalInterface {

	String capitalizeString(String s);
}
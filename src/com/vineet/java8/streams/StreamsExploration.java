package com.vineet.java8.streams;

import java.util.stream.Stream;

public class StreamsExploration {

	public static void main(String[] args) {
		Stream<String> streamGenerated = Stream.generate(() -> "test").limit(10);
		streamGenerated.forEach(System.out::println);
	}
}

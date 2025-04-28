package com.vineet.java8;

import java.util.Optional;

public class OptionalTest {

	public static void main(String[] args) {
		System.out.println(getBike(1).map(Bike::getName).orElse("Default"));
		System.out.println(getBike(2).map(Bike::getName).orElse("Default"));
	}

	public static Optional<Bike> getBike(int number) {
		Bike bike = null;

		if (number % 2 == 0) {
			bike = new Bike("Rizta", "Ather");
		}
		return Optional.ofNullable(bike);
	}
}

class Bike {

	private String name;
	private String brand;

	public Bike(String name, String brand) {
		this.name = name;
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

}

package com.vineet.designpatterns.creational;

public class FactoryPatternExample {

	public static void main(String[] args) {
		// Create a factory
		ShapeFactory shapeFactory = new ShapeFactory();

		// Create a circle
		Shape circle = shapeFactory.getShape("CIRCLE");
		circle.draw();

		// Create a rectangle
		Shape rectangle = shapeFactory.getShape("RECTANGLE");
		rectangle.draw();

	}
}

interface Shape {
	void draw();
}

class Circle implements Shape {
	@Override
	public void draw() {
		System.out.println("Drawing a Circle");
	}
}

class Rectangle implements Shape {
	@Override
	public void draw() {
		System.out.println("Drawing a Rectangle");
	}
}

class ShapeFactory {
	public Shape getShape(String shapeType) {
		if (shapeType == null) {
			return null;
		}
		if (shapeType.equalsIgnoreCase("CIRCLE")) {
			return new Circle();
		} else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
			return new Rectangle();
		}
		return null;
	}
}

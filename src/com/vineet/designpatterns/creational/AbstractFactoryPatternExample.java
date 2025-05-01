package com.vineet.designpatterns.creational;

public class AbstractFactoryPatternExample {

	public static void main(String[] args) {
		
		MealMaker vegMealMaker = new VegMealMaker();
		System.out.println(vegMealMaker.prepareMeal().getMealContents());
		
		MealMaker nonVegMealMaker = new NonVegMealMaker();
		System.out.println(nonVegMealMaker.prepareMeal().getMealContents());
	}
}

interface Meal {
	String getMealContents();
}

class VegMeal implements Meal {
	@Override
	public String getMealContents() {
		return "Veg Meal";
	}
}

class NonVegMeal implements Meal {
	@Override
	public String getMealContents() {
		return "Non-Veg Meal";
	}
}

interface MealMaker {
	Meal prepareMeal();
}

class VegMealMaker implements MealMaker {
	@Override
	public Meal prepareMeal() {
		return new VegMeal();
	}
}

class NonVegMealMaker implements MealMaker {
	@Override
	public Meal prepareMeal() {
		return new NonVegMeal();
	}
}


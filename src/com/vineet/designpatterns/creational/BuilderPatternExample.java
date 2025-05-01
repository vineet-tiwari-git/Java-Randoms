package com.vineet.designpatterns.creational;

public class BuilderPatternExample {

	public static void main(String[] args) {
		FullMeal fullMeal = new FullMeal.Builder().setMealType("Thali").setDrink("Lassi").setDessert("Rabdi").build();
		System.out.println(fullMeal.getMealContents());
	}
}

class FullMeal {
	private String mealType;
	private String drink;
	private String dessert;

	public FullMeal(String mealType, String drink, String dessert) {
		this.mealType = mealType;
		this.drink = drink;
		this.dessert = dessert;
	}

	public String getMealContents() {
		return "Meal Type: " + mealType + ", Drink: " + drink + ", Dessert: " + dessert;
	}

	public static class Builder {
		private String mealType;
		private String drink;
		private String dessert;

		public Builder setMealType(String mealType) {
			this.mealType = mealType;
			return this;
		}

		public Builder setDrink(String drink) {
			this.drink = drink;
			return this;
		}

		public Builder setDessert(String dessert) {
			this.dessert = dessert;
			return this;
		}

		public FullMeal build() {
			return new FullMeal(mealType, drink, dessert);
		}
	}

	@Override
	public String toString() {
		return "FullMeal [mealType=" + mealType + ", drink=" + drink + ", dessert=" + dessert + "]";
	}
}
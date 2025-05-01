package com.vineet.designpatterns.creational;

public class PrototypePatternExample {

	public static void main(String[] args) {
		ModifyableMeal meal1 = new ModifyableMeal("Thali", "Lassi", "Rabdi");
		System.out.println(meal1.getMealContents());

		ModifyableMeal meal2 = new ModifyableMeal(meal1.getMealType(), meal1.getDrink(), meal1.getDessert());
		System.out.println(meal2.getMealContents());

		meal2.setDrink("Water");
		System.out.println(meal1.getMealContents());
		System.out.println(meal2.getMealContents());

		meal2.setDessert("Ice Cream");
		System.out.println(meal1.getMealContents());
		System.out.println(meal2.getMealContents());
	}
}

class ModifyableMeal {

	private String mealType;
	private String drink;
	private String dessert;

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	public void setDrink(String drink) {
		this.drink = drink;
	}

	public void setDessert(String dessert) {
		this.dessert = dessert;
	}

	public String getMealType() {
		return mealType;
	}

	public String getDrink() {
		return drink;
	}

	public String getDessert() {
		return dessert;
	}

	public ModifyableMeal(String mealType, String drink, String dessert) {
		this.mealType = mealType;
		this.drink = drink;
		this.dessert = dessert;
	}

	public String getMealContents() {
		return "Meal Type: " + mealType + ", Drink: " + drink + ", Dessert: " + dessert;
	}

}
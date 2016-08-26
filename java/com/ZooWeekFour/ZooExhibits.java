package com.ZooWeekFour;

public class ZooExhibits {

	private int animal_ID = 0;
	private String species = null;
	private String cageType= null;
	private String food = null;
	private String  name= null;
	private double weight = 0.0;
	
	public ZooExhibits() {
		super();
	}

	public int getAnimal_ID() {
		return animal_ID;
	}

	public void setAnimal_ID(int animal_ID) {
		this.animal_ID = animal_ID;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getCageType() {
		return cageType;
	}

	public void setCageType(String cageType) {
		this.cageType = cageType;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "ZooExhibits [animal_ID=" + animal_ID + ", species=" + species + ", cageType=" + cageType + ", food="
				+ food + ", name=" + name + ", weight=" + weight + "]";
	}

	
	
	
	
	
}

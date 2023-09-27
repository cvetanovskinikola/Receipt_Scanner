package com.example.demo.model;


public class Product {

	private String name;
	private Double weight;
	private String description;
	private boolean domestic;
	private Double price;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isDomestic() {
		return domestic;
	}
	public void setDomestic(boolean domestic) {
		this.domestic = domestic;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Product(String name, Double weight, String description, boolean domestic, Double price) {
		super();
		this.name = name;
		this.weight = weight;
		this.description = description;
		this.domestic = domestic;
		this.price = price;
	}
	
	public Product() {
		super();
	}
	@Override
	public String toString() {
		return "Product [name=" + name + ", weight=" + weight + ", description=" + description + ", domestic="
				+ domestic + ", price=" + price + "]";
	}
	
}

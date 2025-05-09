// 
// Assignment 3 
// Question: Part 1
// Written by: Mamadou Camara(40315009) and Diego Chidiac(40315295) 
// 
package products;

public class Product implements Comparable<Product> {
	protected String productName;
	protected String country;
	protected String category;
	protected double initialPrice;
	protected double finalPrice;
	
	//Parametrized Constructor
	public Product(String productName, String country, String category, double initialPrice) {
		this.productName = productName;
		this.country = country;
		this.category = category;
		this.initialPrice = initialPrice;
		this.finalPrice = 0;
	}
	
	//Default constructor
	public Product() {
		new Product(null,null,null,0);
		this.finalPrice = 0;
	}
	
	//Copy constructor
	public Product(Product otherProduct) {
		new Product(otherProduct.productName,otherProduct.country,otherProduct.category,otherProduct.initialPrice);
		this.finalPrice = otherProduct.finalPrice;
	}
	
	//Getters
	public String getProductName() {
		return this.productName;
	}
	public String getCountry() {
		return this.country;
	}
	public String getCategory() {
		return this.category;
	}
	public double getInitialPrice() {
		return this.initialPrice;
	}
	
	//Setters
	public void setProductName(String newProductName) {
		this.productName = newProductName;
	}
	public void setCountry(String newCountry) {
		this.country = newCountry;
	}
	public void setCategory(String newCategory) {
		this.category = newCategory;
	}
	public void setInitialPrice(double newInitialPrice) {
		this.initialPrice = newInitialPrice;
	}
	public void setFinalPrice(double newFinalPrice) {
		this.finalPrice = newFinalPrice;
	}
	//Sort
    public int compareTo(Product other) {
        return this.productName.compareTo(other.productName);
    }
	
	//Equals method
	public boolean equals(Object otherObject) {
		//Check if object is null
		if(otherObject == null) {
			return false;
		}
		//Check if object is a product
		if(this.getClass() != otherObject.getClass()) {
			return false;
		}
		//Create a product object since we are sure it is one at this point
		Product otherProduct = (Product) otherObject;
		
		double e = 0.000000001;
		double diff = Math.abs(this.initialPrice - otherProduct.initialPrice);
		
		return this.productName.equals(otherProduct.productName) && this.country.equals(otherProduct.country)
				 && this.category.equals(otherProduct.category) && diff < e
				;
		
	}
	//toString method
	public String toString() {
		return this.productName + "," + this.country + "," + this.category + "," + this.finalPrice;
	}
	
}
// 
// Assignment 3 
// Question: Part 2
// Written by: Mamadou Camara(40315009) and Diego Chidiac(40315295) 
// 
package tariffs;

public class Tariff {
	protected String destinationCountry;
	protected String originCountry;
	protected String productCategory;
	protected double minimumTariff;
	
	//Parameterized constructor
	public Tariff(String destinationCountry, String originCountry, String productCategory, double minimumTariff) {
		this.destinationCountry = destinationCountry;
		this.originCountry = originCountry;
		this.productCategory = productCategory;
		this.minimumTariff = minimumTariff;
	}
	
	//Default constructor
	public Tariff() {
		this(null,null,null,0);
	}
	
	//Copy constructor
	public Tariff(Tariff otherTariff) {
		this(otherTariff.destinationCountry,otherTariff.originCountry,otherTariff.productCategory,otherTariff.minimumTariff);
	}
	
	//Clone method
	public Tariff clone(Tariff otherTariff) {
		return new Tariff(otherTariff);
	}
	
	//Getters
	public String getDestinationCountry() {
		return this.destinationCountry;
	}
	public String getOriginCountry() {
		return this.originCountry;
	}
	public String getProductCategory() {
		return this.productCategory;
	}
	public double getMinimumTariff() {
		return this.minimumTariff;
	}
	
	//Setters
	public void setDestinationCountry(String newDestinationCountry) {
		this.destinationCountry = newDestinationCountry;
	}
	public void setOriginCountry(String newOriginCountry) {
		this.originCountry = newOriginCountry;
	}
	public void setProductCategory(String newProductCategory) {
		this.productCategory = newProductCategory;
	}
	public void setMinimumtariff(double newMinimumTariff) {
		this.minimumTariff = newMinimumTariff;
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
		Tariff otherTariff = (Tariff) otherObject;
		
		double e = 0.000000001;
		double diff = Math.abs(this.minimumTariff - otherTariff.minimumTariff);
		
		return this.destinationCountry.equals(otherTariff.destinationCountry) && this.originCountry.equals(otherTariff.originCountry)
				 && this.productCategory.equals(otherTariff.productCategory) && diff < e;
		
	}
	
	//toString method
	public String toString() {
		return "Destination Country: " + this.destinationCountry + " Origin Country: " + this.originCountry
				+ " Product Category: " + this.productCategory + " Minimum Tariff: " + this.minimumTariff;
	}
}
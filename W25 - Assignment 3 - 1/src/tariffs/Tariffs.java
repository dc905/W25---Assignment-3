// 
// Assignment 3 
// Question: Part 1
// Written by: Mamadou Camara(40315009) and Diego Chidiac(40315295) 
// 
package tariffs;

public class Tariffs {
	 public double application(String country, String category, double initialPrice) {
		 	double finalPrice = 0;
		 	
		 	switch(country) {
		 	case "China":
		 		finalPrice = initialPrice + (initialPrice * 0.25);
		 		break;
	 		case "USA":
		 		if(category.equals("Electronics")) {
		 		finalPrice = initialPrice + (initialPrice * 0.10);
		 		}
		 		else {
		 		finalPrice = initialPrice;
		 		}
		 		break;
	 		case "Japan":
	 			if(category.equals("Automobile")) {
	 			finalPrice = initialPrice + (initialPrice * 0.15);
	 			}
		 		else {
		 		finalPrice = initialPrice;
		 		}
	 			break;
	 		case "India":
	 			if(category.equals("Agriculture")) {
	 			finalPrice = initialPrice + (initialPrice * 0.05);
	 			}
		 		else {
		 		finalPrice = initialPrice;
		 		}
	 			break;
	 		case "South Korea":
	 			if(category.equals("Electronics")) {
	 			finalPrice = initialPrice + (initialPrice * 0.08);
	 			}
		 		else {
		 		finalPrice = initialPrice;
		 		}
	 			break;
	 		
	 		case "Saudi Arabia":
	 			if(category.equals("Energy")) {
	 			finalPrice = initialPrice + (initialPrice * 0.12);
	 			}
		 		else {
		 		finalPrice = initialPrice;
		 		}
	 			break;
	 		case "German":
	 			if(category.equals("Manufacturing")) {
	 			finalPrice = initialPrice + (initialPrice * 0.06);
	 			}
		 		else {
		 		finalPrice = initialPrice;
		 		}
	 			break;
	 		case "Bangladesh":
	 			if(category.equals("Textile")) {
	 			finalPrice = initialPrice + (initialPrice * 0.04);
	 			}
		 		else {
		 		finalPrice = initialPrice;
		 		}
	 			break;	 			 			 		
	 		case "Brazil":
	 			if(category.equals("Agriculture")) {
	 			finalPrice = initialPrice + (initialPrice * 0.09);
	 			}
		 		else {
		 		finalPrice = initialPrice;
		 		}
	 			break;		
		 	}	 	
		return finalPrice;
		}
	 }
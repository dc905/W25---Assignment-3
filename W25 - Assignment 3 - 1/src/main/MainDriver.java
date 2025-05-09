// 
// Assignment 3 
// Question: Part 1
// Written by: Mamadou Camara(40315009) and Diego Chidiac(40315295) 
// 
package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import products.Product;
import tariffs.Tariffs;

public class MainDriver {

	public static void main(String[] args) {
		
		try {
			//Creating the streams
			BufferedReader inputStream = new BufferedReader(new FileReader("TradeData.txt"));
			PrintWriter outputStream = new PrintWriter(new FileOutputStream("UpdatedTradeData.txt"));
			
			ArrayList<Product> products = new ArrayList<>();
			String line;
			
			while((line = inputStream.readLine()) != null) {
				//Reading the lines of the text file to create the Product objects	
				String[] values = line.split(","); 
					
                String productName = values[0].trim();
                String country = values[1].trim();
                String category = values[2].trim();
                double initialPrice;
				
                //Make sure a number is written in the initial
                try {
                    initialPrice = Double.parseDouble(values[3].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price format in line: " + line);
                    continue;
                }
                
				//Creating the product object
				Product product = new Product(productName, country, category, initialPrice);
					
				//Putting the product into the ArrayList
				products.add(product);
			}
			
			//Sort the ArrayList alphabetically
			products.sort(null);
			
			//Print every product in the ArrayList
			for (Product product : products) {
				Tariffs tariffs = new Tariffs();
				product.setFinalPrice(tariffs.application(product.getCountry(), product.getCategory(), product.getInitialPrice()));
				outputStream.println(product);
			}
			
			System.out.println("Finished");
			//Close both streams
			inputStream.close();
			outputStream.close();
				
		} catch(FileNotFoundException fnfe) {
			System.out.println("File TradeData.txt was not found ");
			System.out.println("or could not be opened");
			
		} catch (IOException e) {
			System.out.println("Error reading from TradeData.txt");
		}
	}

}
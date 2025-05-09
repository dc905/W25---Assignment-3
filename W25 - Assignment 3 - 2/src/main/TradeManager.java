// 
// Assignment 3 
// Question: Part 2
// Written by: Mamadou Camara(40315009) and Diego Chidiac(40315295) 
// 
package main;

import tariffs.TariffList;
import tariffs.Tariff;

import java.io.*;
import java.util.*;

public class TradeManager {

	public static void main(String[] args) {
		System.out.println("Welcome to TradeManager by Mamadou Camara and Diego Chidiac!");
		// Part a) Create two empty TariffLists
		TariffList list1 = new TariffList();
		TariffList list2 = new TariffList();
		
		// Part b) Read Tariff.txt and add to list1
		try {
			BufferedReader reader = new BufferedReader(new FileReader("Tariffs.txt"));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] values = line.trim().split("\\s+"); // split on any space
				
				String destination = values[0].trim();
				String origin = values[1].trim();
				String category = values[2].trim();
				double minTariff;
				//Make sure a number is written in the initial
                try {
                    minTariff = Double.parseDouble(values[3].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price format in line: " + line);
                    continue;
                }
                
                Tariff t = new Tariff(destination, origin, category, minTariff);
                // Make sure there are no duplicate records before adding
                if (!list1.contains(origin, destination, category)) {
                	list1.addToStart(t);
                }
			}
		} catch(FileNotFoundException fnfe) {
			System.out.println("File Tariffs.txt was not found ");
			System.out.println("or could not be opened");
			
		} catch (IOException e) {
			System.out.println("Error reading from Tariffs.txt");
		}
		
		// Part c) Read TradeRequests.txt and process each request
		ArrayList<String> requests = new ArrayList<>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("TradeRequests.txt"));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.trim() != null) {
					requests.add(line.trim());
				}
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("File TradeRequests.txt not found.");
		} catch (IOException e) {
			System.out.println("Error reading from TradeRequests.txt");
		}
		
		System.out.println("\n> Processing Trade Requests... \n");
		
		for (String request : requests) {
			String[] parts = request.split("\\s+");
			
			String reqID = parts[0].trim();
			String origin = parts[1].trim();
			String destination = parts[2].trim();
			String category = parts[3].trim();
			double tradeValue = Double.parseDouble(parts[4].trim());
			double proposedTariff = Double.parseDouble(parts[5].trim());

			// Find the matching tariff
			TariffList.TariffNode node = list1.find(origin, destination, category);

			if (node == null) {
				System.out.println(reqID + " - Rejected. No tariff policy found.");
				continue;
			}
			
			double minTariff = node.getMinimumTariff(); //Goes to node, gets the Tariff object and then its minimumTariff, the getter is in the TariffNode inner class
			
			// Use evaluateTrade() to determine if it is accepted, conditionally accepted or rejected
			String result = list1.evaluateTrade(proposedTariff, minTariff);
			
			switch (result) {
			case "accepted":
				System.out.println(reqID + " - Accepted.");
				break;
			case "conditionally accepted":
				double surcharge = tradeValue * ((minTariff - proposedTariff) / 100.0);
				System.out.printf("%s - Conditionally Accepted. Surcharge of $%.2f applied.%n", reqID, surcharge);
				break;
			case "rejected":
				System.out.println(reqID + " - Rejected. Proposed tariff too low.");
				break;
			}
		}
		
		// Part d) Prompt user to search list
		Scanner sc = new Scanner(System.in);
		System.out.println("\n> Search Tariffs List");
		
		System.out.print("\nEnter origin country: ");
		String origin = sc.nextLine();
		
		System.out.print("Enter destination country: ");
		String destination = sc.nextLine();
		
		System.out.print("Enter product category: ");
		String category = sc.nextLine();
		
		TariffList.TariffNode match = list1.find(origin, destination, category);
		if (match == null) {
			System.out.println("No matching tariff found.");
		} else {
			System.out.println("Found: " + match);
		}
		
		// Part e) Testing
		System.out.println("\n> Testing Methods...");

		// Create a sample Tariff
		Tariff t1 = new Tariff("Canada", "USA", "Automobiles", 15.0);
		Tariff t2 = new Tariff(t1); // Copy constructor
		Tariff t3 = t1.clone(t1);   // Clone method

		System.out.println("Tariff t1: " + t1);
		System.out.println("Tariff t2 (copy): " + t2);
		System.out.println("Tariff t3 (clone): " + t3);
		System.out.println("t1 equals t2? " + t1.equals(t2));

		// Create a TariffList and test insert/replace/delete
		TariffList testList = new TariffList();
		testList.addToStart(t1); // Add to start
		testList.insertAtIndex(new Tariff("Germany", "France", "Electronics", 10.0), 0); // Insert at index 0
		testList.addToStart(new Tariff("Japan", "India", "Agriculture", 7.0));
		testList.replaceAtIndex(new Tariff("UK", "India", "Textile", 8.5), 1); // Replace at index
		testList.display(); // Display list
		testList.deleteFromIndex(1); // Delete one
		testList.deleteFromStart(); // Delete start
		testList.display(); // Final display
	}
}

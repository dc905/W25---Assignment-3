// 
// Assignment 3 
// Question: Part 2
// Written by: Mamadou Camara(40315009) and Diego Chidiac(40315295) 
// 
package tariffs;

public interface TariffPolicy {
	
	public String evaluateTrade(double proposedTariff, double minimumTariff);
}
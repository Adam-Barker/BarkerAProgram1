//FILE: Cab.Java
//PROG:	Adam Barker
//PURP:	Simulates a taxi cab with gas mileage, gas tank, and fare
package edu.trident.barker.cpt237;

public class Cab {
	protected double totalMiles;
	protected double gasAvailable;
	protected double milesAvailable;
	protected double milesDriven;
	protected double grossEarnings;
	protected double totalFare;
	protected double gasTank = 22.9;
	
	public Cab() {
	}

	public double getTotalFare() {
		totalFare = Math.round(totalFare * 100.0) / 100.0;
		return totalFare;
	}

	public double getMiles() {
		return totalMiles;
	}

	public double getGasAvailable() {
		gasAvailable = Math.round(gasAvailable * 100.0) / 100.0;
		return gasAvailable;
	}

	public double getMilesAvailable() {
		milesAvailable = Math.round(milesAvailable * 100.0) / 100.0;
		return milesAvailable;
	}

	public double getMilesDriven() {
		return milesDriven;
	}

	public double getGrossEarnings() {
		grossEarnings = Math.round(grossEarnings * 100.0) / 100.0;

		return grossEarnings;
	}

	public double getTotalMiles() {
		totalMiles = Math.round(totalMiles * 100.0) / 100.0;
		return totalMiles;
	}

	public void setGasAvailable(double gasInput) {
		if (gasTank < (gasAvailable + gasInput))
			gasAvailable = gasTank;
		else
			gasAvailable += gasInput;
	}

	public void milesAvailable() {
		double milesPerGallon = 17.8;
		milesAvailable = gasAvailable * milesPerGallon;
	}

	public void trip(double miles) {
		double milesPerGallon = 17.8;
		double initFare = 2.00;
		double mileFare = .585;
		if (milesAvailable > miles) {
			milesAvailable -= miles;
			gasAvailable -= (miles / milesPerGallon);
			totalMiles += miles;
			totalFare = initFare + (mileFare * miles);
			grossEarnings += totalFare;
		}
	}

	public void reset() {
		totalMiles = 0;
		grossEarnings = 0;
	}

}

//FILE: Cab.Java
//PROG:	Adam Barker
//PURP:	Simulates a taxi cab with gas mileage, gas tank, and fare
package edu.trident.barker.cpt237;

public class Cab {
	private double tripMiles;
	private double totalMiles;
	private double gasAvailable = 22.9;
	private double milesAvailable;
	private double milesDriven;
	private double grossEarnings;
	private double totalFare;
	private String outputText;

	public Cab() {
	}

	public String getOutputText() {
		return outputText;
	}

	public double getTripMiles() {
		return tripMiles;
	}

	public double getTotalFare() {
		return totalFare;
	}

	public double getMiles() {
		return totalMiles;
	}

	public double getGasAvailable() {
		return gasAvailable;
	}

	public double getMilesAvailable() {
		return milesAvailable;
	}

	public double getMilesDriven() {
		return milesDriven;
	}

	public double getGrossEarnings() {
		return grossEarnings;
	}

	public double getTotalMiles() {
		return totalMiles;
	}

	public void setGasAvailable(double gasInput) {
		double gasTank = 22.9;
		if (gasTank < (gasAvailable + gasInput))
			gasAvailable = gasTank;
		else
			gasAvailable += gasInput;
	}

	public void milesAvailable() {
		double milesPerGallon = 17.8;
		milesAvailable = gasAvailable * milesPerGallon;
		milesAvailable = Math.round(milesAvailable * 100.0) / 100.0;
	}

	public void trip(double miles) {
		double milesPerGallon = 17.8;
		double initFare = 2.00;
		double mileFare = .585;
		this.milesAvailable();
		if (milesAvailable > miles) {
			milesAvailable -= miles;
			milesAvailable = Math.round(milesAvailable * 100.0) / 100.0;
			gasAvailable -= (miles / milesPerGallon);
			gasAvailable = Math.round(gasAvailable * 100.0) / 100.0;
			totalMiles += miles;
			totalMiles = Math.round(totalMiles * 100.0) / 100.0;
			totalFare = initFare + (mileFare * miles);
			totalFare = Math.round(totalFare * 100.0) / 100.0;
			outputText = Double.toString(totalFare);
			grossEarnings += totalFare;
			grossEarnings = Math.round(grossEarnings * 100.0) / 100.0;
		} else
			outputText = "Not enough Gas!";
	}

	public void reset() {
		totalMiles = 0;
		grossEarnings = 0;
		outputText = "Earnings and Mileage have been reset!";
	}

}

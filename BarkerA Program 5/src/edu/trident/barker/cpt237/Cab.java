//FILE: Cab.Java
//PROG:	Adam Barker
//PURP:	Simulates a taxi cab with gas mileage, gas tank, and fare
package edu.trident.barker.cpt237;

public class Cab {

	
	private final double INITIAL_FARE = 2.00;
	private final double MILE_FARE = .585;
	protected double mpg;
	protected double gasTank = 0;
	protected double totalMiles;
	protected double gasAvailable;
	protected double milesAvailable;
	protected double milesDriven;
	protected double grossEarnings;
	protected double totalFare;
	protected double gasInput;

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
		milesAvailable = gasAvailable * mpg;
		milesAvailable = Math.round(milesAvailable * 100.0) / 100.0;
		return milesAvailable;
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
		if (gasTank < (gasAvailable + gasInput)){
			gasAvailable = gasTank;
			this.gasInput = gasTank - gasAvailable;
		}
		else{
			gasAvailable += gasInput;
			this.gasInput = gasInput;
		}
	}

	public void milesAvailable() {
	}

	public void trip(double miles) {
		if (gasAvailable * mpg > miles) {
			gasAvailable -= (miles / mpg);
			totalMiles += miles;
			totalFare = INITIAL_FARE + (MILE_FARE * miles);
			grossEarnings += totalFare;
		}
	}

	public void reset() {
		totalMiles = 0;
		grossEarnings = 0;
	}

}

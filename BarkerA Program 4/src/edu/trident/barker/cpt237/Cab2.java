//FILE: Cab2.Java
//PROG:	Adam Barker
//PURP:	Extends Cab.java for added functionality
package edu.trident.barker.cpt237;


public class Cab2 extends Cab {
	private final double SERVICE_COST = 25.00;
	private double serviceMiles;
	private double maintenanceCost;
	private double netEarns;
	private String name;
	
	public Cab2(String name, double gasTank, double mpg) {
		super();
		this.gasTank = gasTank;
		this.mpg = mpg;
		this.name = name;
	}

	public String getName(){
		return name;
	}
	
	public double getServiceMiles() {
		return serviceMiles;
	}

	public double getMaintenanceCost() {
		return maintenanceCost;
	}

	public double getNetEarns() {
		netEarns = Math.round(netEarns * 100.00) / 100.00;
		return netEarns;
	}

	public void incrementServiceMiles(double miles) {
		serviceMiles += miles;
	}

	/**
	 * Sets miles recorded as the miles since the object was last "serviced"
	 * Runs the overwritten method "trip()" that records various values of the
	 * simulated trip.
	 * 
	 * @author Adam Barker
	 * @param miles
	 *            Simulates the amount of miles the cab has driven.
	 */
	public void trip(double miles) {
			super.trip(miles);
			incrementServiceMiles(miles);
			repNetEarns();
	}

	/**
	 * Calculates net earnings from the cab object.
	 * 
	 * @author Adam Barker
	 * 
	 */
	public void repNetEarns() {
		netEarns = grossEarnings - maintenanceCost;
	}

	/**
	 * Simulates servicing a cab. Also resets miles since last cab "service" and
	 * adds a maintenance cost.
	 * 
	 * @author Adam Barker
	 */
	public void service() {
		serviceMiles = 0;
		maintenanceCost += SERVICE_COST;
		repNetEarns();
	}

	/**
	 * Simulates adding gas to cab object. Includes gas price in maintenance
	 * cost.
	 * 
	 * @param gasInput
	 *            Amount of gas put into cab object.
	 * @param gasPrice
	 *            Price of gas being put into cab object.
	 * @author Adam Barker
	 */
	public void addGas(double gasInput, double gasPrice) {
		if ((gasInput + gasAvailable) <= gasTank)
			maintenanceCost += gasInput * gasPrice;
		else
			maintenanceCost += (gasTank - gasAvailable) * gasPrice;
		super.setGasAvailable(gasInput);
		repNetEarns();
	}

	/**
	 * Resets all variable of cab object to zero excluding service related
	 * methods.
	 * 
	 * @author Adam Barker
	 */
	public void reset() {
		super.reset();
		maintenanceCost = 0;
	}
}

//FILE: Cab2.Java
//PROG:	Adam Barker
//PURP:	Extends Cab.java for added functionality
package edu.trident.barker.cpt237;

public class Cab2 extends Cab {
	private final double serviceCost;
	private double serviceMiles;
	private double maintenanceCost;
	private double netEarns;
	private String name;
	ActionRecorder writer = ActionRecorder.getInstance();

	public Cab2(String name, double gasTank, double mpg, double serviceCost) {
		super();
		this.gasTank = gasTank;
		this.mpg = mpg;
		this.name = name;
		this.serviceCost = serviceCost;
	}

	public String getName() {
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
		writer.writeRecord(getName(), RecordType.FARE, miles, totalFare);
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
		writer.writeRecord(getName(), RecordType.SERVICE, getServiceMiles(),
				serviceCost);
		serviceMiles = 0;
		maintenanceCost += serviceCost;
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
	public void addGas(double gasIn, double gasPrice) {
		super.setGasAvailable(gasIn);
		maintenanceCost += gasInput * gasPrice;
		writer.writeRecord(getName(), RecordType.GAS, gasInput, gasPrice);
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

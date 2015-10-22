package edu.trident.barker.cpt237;

import java.util.ArrayList;
import java.util.List;

public class Cab2 extends Cab {
	private final double SERVICE_MAX_MILES = 500.00;
	private final double SERVICE_COST = 25.00;
	private double serviceMiles;
	private double maintenanceCost;
	private double netEarns;
	private boolean overRide;
	private List<MaintenanceListener> listeners = new ArrayList<MaintenanceListener>();

	public Cab2(double gasInput) {
		super();
		setGasAvailable(gasInput);
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
		if (overRide == true) {
			super.trip(miles);
			incrementServiceMiles(miles);
			overRide = false;
		} else if (serviceMiles < SERVICE_MAX_MILES) {
			super.trip(miles);
			incrementServiceMiles(miles);
		} else {
			notifyListeners(true);
		}

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
		notifyListeners(false);
		serviceMiles = 0;
		maintenanceCost += SERVICE_COST;
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
		if ((gasInput + gasAvailable) <= GAS_TANK)
			maintenanceCost += gasInput * gasPrice;
		else
			maintenanceCost += (GAS_TANK - gasAvailable) * gasPrice;
		super.setGasAvailable(gasInput);

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

	/**
	 * Sets variable overRide to true.
	 * 
	 * @author Adam Barker
	 */
	public void overRide() {
		overRide = true;
		notifyListeners(false);
	}

	/**
	 * Adds custom listeners to an array list of listeners.
	 * 
	 * @param listener
	 * @author Adam Barker
	 */
	public void addMaintenanceListener(MaintenanceListener listener) {
		listeners.add(listener);
	}

	/**
	 * Fires events for listeners in array list.
	 * 
	 * @param alert
	 * @author Adam Barker
	 */
	private void notifyListeners(boolean alert) {
		for (MaintenanceListener ui : listeners) {
			if (alert == true)
				ui.maintenanceAlert(MaintenanceListener.SERVICE_NEEDED);
			if (alert == false)
				ui.maintenanceAlert(MaintenanceListener.SERVICED);
		}
	}
}

package edu.trident.barker.cpt237;

public interface MaintenanceListener {
	final int SERVICE_NEEDED = 1;
	final int SERVICED = 2;

	void maintenanceAlert(int trigger);
}

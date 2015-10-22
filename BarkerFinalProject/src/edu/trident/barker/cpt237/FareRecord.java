package edu.trident.barker.cpt237;

public class FareRecord extends GenericCabRecord {

	private double miles = 0;
	private double fare = 0;

	public FareRecord(String date, String cabID, String value1, String value2) {
		super(date);
		miles = Double.parseDouble(value1);
		fare = Double.parseDouble(value2);
	}

	public double getMiles() {
		return miles;
	}

	public double getFare() {
		return fare;
	}

}

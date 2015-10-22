package edu.trident.barker.cpt237;

public class ServiceRecord extends GenericCabRecord{
	private double miles;
	private double price;
	
	public ServiceRecord(String date, String cabID, String value1, String value2){
		super(date);
		miles = Double.parseDouble(value1);
		price = Double.parseDouble(value2);
	}

	public double getMiles() {
		return miles;
	}

	public double getPrice() {
		return price;
	}

}

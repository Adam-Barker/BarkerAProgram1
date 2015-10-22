package edu.trident.barker.cpt237;

public class GasRecord extends GenericCabRecord {
	private double gas;
	private double gasPrice;

	public GasRecord(String date, String cabID, String value1, String value2){
		super(date);
		gas = Double.parseDouble(value1);
		gasPrice = Double.parseDouble(value2);
	}

	public double getGas() {
		return gas;
	}

	public double getGasPrice() {
		return gasPrice;
	}

}

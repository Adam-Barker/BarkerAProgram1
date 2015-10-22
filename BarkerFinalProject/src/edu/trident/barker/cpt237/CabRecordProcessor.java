package edu.trident.barker.cpt237;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CabRecordProcessor {
	String filePath;
	CabInfo cab;
	double grossEarns;
	double totalGasCost;
	double totalServiceCost;
	double netEarns;
	double totalMiles;
	double averageService;
	ArrayList<CabInfo> cabList = new ArrayList<CabInfo>();
	ActionRecorder record = ActionRecorder.getInstance();

	public CabRecordProcessor(String filePath) {
		this.filePath = filePath;
	}

	private CabInfo setCab(String cabName) {
		CabInfo cab = null;
		for (CabInfo c : cabList) {
			if (cabName.equals(c.getName()))
				cab = c;
		}

		return cab;
	}

	public void loadRecords() {
		String line = "";
		int serviceAccumulator = 0;
		try {
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			while ((line = reader.readLine()) != null) {
				String[] tokens;
				String date;
				String cabName;
				String type;
				String value1;
				String value2;

				try {
					tokens = line.split(",");
					date = tokens[0];
					cabName = tokens[1];
					type = tokens[2];
					value1 = tokens[3];
					value2 = tokens[4];

					cab = setCab(cabName);

					if (cab == null) {
						cab = new CabInfo(cabName);
						cabList.add(cab);
					}

					if ("FARE".equalsIgnoreCase(type)) {
						FareRecord record = new FareRecord(date, cabName,
								value1, value2);
						cab.addFareRecord(record);
						grossEarns += Double.parseDouble(value2);
						totalMiles += Double.parseDouble(value1);
					}

					else if ("GAS".equalsIgnoreCase(type)) {
						GasRecord record = new GasRecord(date, cabName, value1,
								value2);
						cab.addGasRecord(record);
						totalGasCost += Double.parseDouble(value2);
					}

					else {
						ServiceRecord record = new ServiceRecord(date, cabName,
								value1, value2);
						cab.addServiceRecord(record);
						totalServiceCost += Double.parseDouble(value2);
						serviceAccumulator++;
						averageService = totalMiles / serviceAccumulator;
					}

					netEarns = grossEarns - (totalServiceCost + totalGasCost);
					GenericCabRecord record = new GenericCabRecord(date);
					cab.addGenericRecord(record);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void produceReport() throws IOException {

		System.out.println("Cab Report");
		System.out.printf("Gross Earnings: $%.2f\n", grossEarns);
		System.out.printf("Gas Cost: $%.2f\n", totalGasCost);
		System.out.printf("Service Cost: $%.2f\n", totalServiceCost);
		System.out.printf("Miles Driven: %.2f\n", totalMiles);
		System.out.printf("Average Service Miles: %.2f\n", averageService);

		for (CabInfo c : cabList) {
			record.writeToCsv(c.getName(), c.getStartDate(), c.getEndDate(),
					c.totalFare(), c.totalGasCost(), c.totalServCost(),
					c.getNetEarns(), c.totalMiles(), c.serviceDays(),
					c.averageService(), c.maximumService());
		}
		record.closeWriter();
	}

	public static void main(String[] args) {
		CabRecordProcessor c = new CabRecordProcessor("CabsOutput.csv");
		try {
			c.loadRecords();
			c.produceReport();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

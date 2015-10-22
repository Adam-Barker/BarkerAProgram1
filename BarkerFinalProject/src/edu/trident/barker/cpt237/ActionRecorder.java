package edu.trident.barker.cpt237;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionRecorder {

	private static ActionRecorder instance = new ActionRecorder();
	private BufferedWriter record = null;
	private String directory = "C:/Users/user/workspace/BarkerFinalProject/cabRecords.csv";

	private ActionRecorder() {
	}

	public static ActionRecorder getInstance() {
		return instance;

	}
	
	public void closeWriter(){
		try {
			record.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeToCsv(String cabName, String firstDate, String lastDate,
			double grossEarns, double gasPrice, double servicePrice, double netEarns,
			double totalMiles, int numberServices, double averageServices,
			double maxServices) {

		File file = new File(directory);
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/dd");
		String delim = ",";

		try {
			if (!file.exists()) {
				record = new BufferedWriter(new FileWriter(directory, true));
				record.append("Report Date" + delim + "Cab ID" + delim
						+ "Start Date" + delim + "End Date" + delim
						+ "Gross Earnings" + delim + "Gas Cost" + delim
						+ "Service Cost" + delim + "Net Earnings" + delim
						+ "Total Miles" + delim + "# Services" + delim
						+ "Average Service In Days" + delim
						+ "Max Service Days" + "\n");
				record.flush();
			}
			record = new BufferedWriter(new FileWriter(directory, true));
			record.append(String.valueOf(formatter.format(date)) + delim
					+ cabName + delim + String.valueOf(firstDate) + delim
					+ String.valueOf(lastDate) + delim + String.format("$%.2f",grossEarns)
					+ delim + String.format("$%.2f",gasPrice) + delim
					+ String.format("$%.2f",servicePrice) + delim + String.format("$%.2f",netEarns)
					+ delim + String.format("%.2f", totalMiles) + delim
					+ String.valueOf(numberServices) + delim
					+ String.format("%.2f", averageServices) + delim
					+ String.valueOf(maxServices) + "\n");
			record.flush();
		} catch (IOException e) {
			System.out.println("Close Output File!");
		}
	}
}

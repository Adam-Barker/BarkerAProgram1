//FILE: ActionRecorder.Java
//PROG:	Adam Barker
//PURP:	Singelton class for writing to a .csv file
package edu.trident.barker.cpt237;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionRecorder {
	private static ActionRecorder instance = new ActionRecorder();
	private String fileName = "C:/Users/User/workspace/BarkerA Program 5/CabsOutput.csv";
	File file = new File(fileName);

	private ActionRecorder() {
	};

	public static ActionRecorder getInstance() {
		return instance;
	}

	/**
	 * Writes Cab Records to .csv file
	 * 
	 * @param cabName
	 * @param type
	 * @param value1
	 * @param value2
	 * @author Adam Barker
	 */

	public void writeRecord(String cabName, RecordType type, double value1,
			double value2) {
		String delim = ",";
		String carReturn = "\n";
		String pattern = "yyyy/MM/dd";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter cabWriter = new FileWriter(fileName, true);
			Date date = new Date();
			cabWriter.append(String.valueOf(format.format(date)));
			cabWriter.append(delim);
			cabWriter.append(cabName);
			cabWriter.append(delim);
			cabWriter.append(String.valueOf(type));
			cabWriter.append(delim);
			cabWriter.append(String.valueOf(value1));
			cabWriter.append(delim);
			cabWriter.append(String.valueOf(value2));
			cabWriter.append(carReturn);
			cabWriter.flush();
			cabWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

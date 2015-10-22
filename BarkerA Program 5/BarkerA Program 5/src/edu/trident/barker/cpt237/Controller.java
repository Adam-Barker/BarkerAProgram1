//FILE: Controller.Java
//PROG:	Adam Barker
//PURP:	Instantiates Cab objects and passes Cab objects into UI
package edu.trident.barker.cpt237;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller implements CabLoader {
	private Cab2 cab;
	private List<Cab2> cabs = new ArrayList<Cab2>();
	String cabsCsv = "Cabs.csv";
	BufferedReader reader = null;

	public Controller() {
	}

	/**
	 * Constructs Cab Objects and puts them in an ArrayList. Instantiates
	 * MultiCabUI.java
	 *
	 * @author Adam Barker
	 */
	public void contsruct() {
		csvToArray();

		@SuppressWarnings("unused")
		MultiCabUI cabUi = new MultiCabUI(setCabNames(), this);
	}

	/**
	 * Loads Cab Objects into MultiCabUI.java
	 *
	 * @author Adam Barker
	 */
	@Override
	public Cab2 loadCab(String name) {
		for (Cab2 cabName : cabs) {
			if (cabName.getName() == name)
				cab = cabName;
		}
		return cab;
	}

	/**
	 * Returns string values of Cab Objects ArrayList's names and populates them
	 * into an array
	 * 
	 * @return
	 */
	public String[] setCabNames() {
		String[] cabNames = new String[cabs.size()];
		for (int i = 0; i < cabs.size(); i++) {
			cabNames[i] = cabs.get(i).getName();
		}
		return cabNames;
	}

	public void csvToArray() {
		String delim = ",";
		String line = "";
		try {
			reader = new BufferedReader(new FileReader(cabsCsv));
			while ((line = reader.readLine()) != null) {
				String[] csvTokens = line.split(delim);
				cabs.add(new Cab2(csvTokens[0], Double
						.parseDouble(csvTokens[1]), Double
						.parseDouble(csvTokens[2]), Double
						.parseDouble(csvTokens[3])));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
//FILE: Controller.Java
//PROG:	Adam Barker
//PURP:	Instantiates Cab objects and passes Cab objects into UI
package edu.trident.barker.cpt237;

import java.util.ArrayList;
import java.util.List;

public class Controller implements CabLoader {
	private Cab2 cab;
	private List<Cab2> cabs = new ArrayList<Cab2>();

	public Controller() {
	}

	/**
	 * Constructs Cab Objects and puts them in an ArrayList. Instantiates
	 * MultiCabUI.java
	 *
	 * @author Adam Barker
	 */
	public void contsruct() {
		cabs.add(new Cab2("Cab1", 25, 33));
		cabs.add(new Cab2("Cab2", 21, 28));
		cabs.add(new Cab2("Cab3", 23, 19));
		cabs.add(new Cab2("Cab4", 16, 22));
		cabs.add(new Cab2("Cab5", 18, 26));


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
		for(Cab2 cabName : cabs){
			if(cabName.getName() == name)
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
}
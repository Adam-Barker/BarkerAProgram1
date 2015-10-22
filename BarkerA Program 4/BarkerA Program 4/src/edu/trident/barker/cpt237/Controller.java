package edu.trident.barker.cpt237;

import java.util.ArrayList;
import java.util.List;

public class Controller implements CabLoader {
	private Cab2 cab;
	private List<Cab2> cabs = new ArrayList<Cab2>();
 
	public Controller(){}
	
	public void contsruct(){
	cabs.add(cab = new Cab2("Cab1", 25, 15));
	cabs.add(cab = new Cab2("Cab2", 21, 18));
	cabs.add(cab = new Cab2("Cab3", 23, 16));
	cabs.add(cab = new Cab2("Cab4", 16, 26));
	cabs.add(cab = new Cab2("Cab5", 18, 22));
	
	@SuppressWarnings("unused")
	MultiCabUI cabUi = new MultiCabUI(setCabNames(), this);

	}
	
	@Override
	public Cab2 loadCab(String name) {
		switch(name){
		case "Cab1":
			cab = cabs.get(0);
			break;
		case "Cab2":
			cab = cabs.get(1);
			break;
		case "Cab3":
			cab = cabs.get(2);
			break;
		case "Cab4":
			cab = cabs.get(3);
			break;
		case "Cab5":
			cab = cabs.get(4);
			break;
		default:
			cab = cabs.get(0);
			break;
		}
		return cab;
	}
	public String[] setCabNames(){
		String[] cabNames = new String[cabs.size()];
		for(int i=0; i<cabs.size(); i++){
	        	cabNames[i] = cabs.get(i).getName() ;
	        }
		return cabNames;
	}
}
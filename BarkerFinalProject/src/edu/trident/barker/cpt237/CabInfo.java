package edu.trident.barker.cpt237;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.ArrayList;


public class CabInfo{

	private ArrayList<ServiceRecord> serviceRecord = new ArrayList<ServiceRecord>();
	private ArrayList<GasRecord> gasRecord = new ArrayList<GasRecord>();
	private ArrayList<FareRecord> fareRecord = new ArrayList<FareRecord>();
	private ArrayList<GenericCabRecord> genericRecord = new ArrayList<GenericCabRecord>();
	private String cabName;
	
	public CabInfo(String cabName){
		this.cabName = cabName;
	}
	
	public String getStartDate() {
		sortGeneric();
		return String.valueOf(genericRecord.get(0).getDate());
	}


	public String getEndDate() {
		sortGeneric();
		return String.valueOf(genericRecord.get(genericRecord.size() -1).getDate());
	}
	
	
	public void addFareRecord(FareRecord record){
		fareRecord.add(record);
	}
	
	public void addGasRecord(GasRecord record) {
		gasRecord.add(record);
		
	}


	public void addServiceRecord(ServiceRecord record) {
		serviceRecord.add(record);	
	}

	public void addGenericRecord(GenericCabRecord record){
		genericRecord.add(record);
	}
	
	public String getName() {
		return cabName;
	}
	
	public void sortGeneric(){
		Collections.sort(genericRecord, new Comparator<GenericCabRecord>(){
			
			@Override
			public int compare(GenericCabRecord o1, GenericCabRecord o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});
	}
	
	public void sortService(){
		Collections.sort(serviceRecord, new Comparator<ServiceRecord>() {

			@Override
			public int compare(ServiceRecord o1, ServiceRecord o2) {

				return o1.getDate().compareTo(o2.getDate());
			}
		});
	}
	
	public double averageService(){
		double averageService = 0;
		Date firstDate = null;
		Date secondDate = null;
		double difference;
		
		sortService();
		
		try {
		if(serviceRecord.size()!=0){
		firstDate = new SimpleDateFormat("MM/dd/yyyy").parse(String.valueOf(serviceRecord.get(0).getDate()));
		secondDate = new SimpleDateFormat("MM/dd/yyyy").parse(String.valueOf(serviceRecord.get(serviceRecord.size()-1).getDate()));
				difference = (secondDate.getTime() - firstDate.getTime());
		averageService = (difference/serviceRecord.size())/(1000 * 60 * 60 * 24);
		}
		}
		catch (ParseException e) {
		e.printStackTrace();
		}
		return averageService;
	}
	
	public double maximumService(){
		double maximumService = 0;
		double difference1 = 0;
		double difference2 = 0;
		Date date1;
		Date date2;
		
		if(serviceRecord.size()!=0){	
			for(int i=0; i<(serviceRecord.size()-1); i++){
				try {
					date1 = new SimpleDateFormat("MM/dd/yyyy").parse(String.valueOf(serviceRecord.get(i).getDate()));
					date2 = new SimpleDateFormat("MM/dd/yyyy").parse(String.valueOf(serviceRecord.get(i+1).getDate()));
					difference1 = (date2.getTime() - date1.getTime())/(1000 * 60 * 60 *24);
						if(difference1 < difference2)
							maximumService = difference2;
						else
							maximumService = difference1;
					difference2 = difference1;
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return maximumService;
	}
	
	public double totalFare(){
		double totalFare = 0;
		
		for (FareRecord f : fareRecord ){
			totalFare += f.getFare();
		}
		return totalFare;
	}
	
	public double totalGasCost(){
		double totalGas = 0;
		for(GasRecord g : gasRecord){
			totalGas += g.getGas();
		}
		
		return totalGas;
		
	}
	
	public double totalServCost(){
		double totalServCost = 0;
		for(ServiceRecord s : serviceRecord){
			totalServCost += s.getPrice();
		}
		return totalServCost;
	}
	
	public double totalMiles(){
		double totalMiles = 0;
		for(FareRecord f : fareRecord){
			totalMiles += f.getMiles();
		}
		return totalMiles;
	}
	
	public int serviceDays(){
		int serviceAccumulator = 0;
		int i = 0;
		while(serviceRecord.size() > i){
			serviceAccumulator++;
			i++;
		}
		return serviceAccumulator;
	}
	
	public double getNetEarns(){
		double netEarns = totalFare() -(totalServCost() + totalGasCost());
		return netEarns;
	}
	
	public double averageMiles(){
		double averageMiles = totalMiles() / serviceDays();
		return averageMiles;
	}
}

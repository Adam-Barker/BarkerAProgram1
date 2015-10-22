//FILE: Main.Java
//PROG:	Adam Barker
//PURP:	Initiates Cab object and UI
package edu.trident.barker.cpt237;

import javax.swing.JOptionPane;

public class Main 
{
	public static void main(String[] args) 
	{
		boolean done = false;
		while(!done){
			try{
				String strGasInput =JOptionPane.showInputDialog("Please enter initial amount of gas! It's free :)");
				double gasInput = Double.parseDouble(strGasInput);
				Cab2 cab = new Cab2(gasInput);
				@SuppressWarnings("unused")
				UserInterface cabUi = new UserInterface(cab);
				done = true;
			}catch (NumberFormatException  e1) {
				JOptionPane.showMessageDialog(null,
				"That is an invalid number! Please try again!");
			}catch (NullPointerException e2){
				System.exit(0);
			}
		}	
	}
}

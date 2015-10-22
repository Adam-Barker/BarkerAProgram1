//FILE: Main.Java
//PROG:	Adam Barker
//PURP:	Initiates Cab object and UI
package edu.trident.barker.cpt237;

public class Main 
{
	public static void main(String[] args) 
	{
	Cab cab1 = new Cab();
	@SuppressWarnings("unused")
	UserInterface cabUi = new UserInterface(cab1);
	}
}

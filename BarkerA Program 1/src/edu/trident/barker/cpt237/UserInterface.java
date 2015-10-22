//FILE: UserInterface.Java
//PROG:	Adam Barker
//PURP:	Creates a user interface to gather or set information for a cab object
package edu.trident.barker.cpt237;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UserInterface extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int WIDTH = 500;
	static final int HEIGHT = 200;

	JButton addGas;
	JButton recordTrip;
	JButton repGasAvailable;
	JButton repMilesAvailable;
	JButton repMilesDriven;
	JButton repGrossEarnings;
	JButton reset;
	JTextField txtField1;
	JTextField txtField2;
	private Cab cab;

	public UserInterface(Cab cab) {
		this.cab = cab;
		initiate();
	}

	public void initiate() {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		layout.setHgap(35);
		setLayout(layout);
		addGas = new JButton("Add Gas");
		recordTrip = new JButton("Record Trip");
		repGasAvailable = new JButton("Gas Available");
		repMilesAvailable = new JButton("Miles Available");
		repMilesDriven = new JButton("Miles Driven");
		repGrossEarnings = new JButton("Gross Earnings");
		reset = new JButton("Reset");
		txtField1 = new JTextField(20);
		txtField2 = new JTextField(20);

		add(txtField1);
		add(txtField2);
		add(addGas);
		add(recordTrip);
		add(repGasAvailable);
		add(repMilesAvailable);
		add(repMilesDriven);
		add(repGrossEarnings);
		add(reset);
		addGas.addActionListener(this);
		recordTrip.addActionListener(this);
		repGasAvailable.addActionListener(this);
		repMilesAvailable.addActionListener(this);
		repMilesDriven.addActionListener(this);
		repGrossEarnings.addActionListener(this);
		reset.addActionListener(this);
		txtField1.addActionListener(this);
		txtField2.addActionListener(this);
		txtField1.setText(String.valueOf(0));
		txtField2.setText(String.valueOf(0));
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == repGasAvailable) {
			txtField1.setText(String.valueOf(0));
			txtField2.setText(String.valueOf(cab.getGasAvailable()));
		} else if (source == repMilesAvailable) {
			cab.milesAvailable();
			txtField1.setText(String.valueOf(0));
			txtField2.setText(String.valueOf(cab.getMilesAvailable()));
		} else if (source == repMilesDriven) {
			txtField1.setText(String.valueOf(0));
			txtField2.setText(String.valueOf(cab.getTotalMiles()));
		} else if (source == repGrossEarnings) {
			txtField1.setText(String.valueOf(0));
			txtField2.setText(String.valueOf(cab.getGrossEarnings()));
		} else if (source == reset) {
			cab.reset();
			txtField1.setText(String.valueOf(0));
			txtField2.setText(cab.getOutputText());
		} else {
			String input1 = txtField1.getText();
			try {
				double num1 = Double.parseDouble(input1);
				if (source == addGas) {
					double gasInput = num1;
					cab.setGasAvailable(gasInput);
					txtField2.setText(String.valueOf(cab.getGasAvailable()));
				} else if (source == recordTrip) {
					double miles = num1;
					cab.trip(miles);
					txtField2.setText(cab.getOutputText());
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null,
						"That is an invalid number!");
			}
		}
	}

}

package edu.trident.barker.cpt237;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class UserInterface2 extends JFrame implements ActionListener,
		ItemListener, MaintenanceListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static final int WIDTH = 500;
	static final int HEIGHT = 250;
	static final String TITLE = "Cab Management";

	JButton okBtn;
	JRadioButton addGas;

	JRadioButton repMilesDriven;
	JRadioButton repGrossEarnings;
	JRadioButton repNetEarnings;
	JRadioButton reset;
	JRadioButton overRide;
	JTextField txtField1;
	private Cab2 cab;

	public UserInterface2(Cab2 cab) {
		this.cab = cab;
		initiate();
	}

	/**
	 * Method that creates and maintains the user interface for the cab object.
	 * 
	 * @author Adam Barker @
	 */
	public void initiate() {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(TITLE);
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		layout.setHgap(35);
		setLayout(layout);
		ButtonGroup bGroup = new ButtonGroup();
		cab.addMaintenanceListener(this);
		okBtn = new JButton("OK");
		repMilesDriven = new JRadioButton("Miles Driven");
		repGrossEarnings = new JRadioButton("Gross Earnings");
		repNetEarnings = new JRadioButton("Net Earnings");
		reset = new JRadioButton("Reset");
		overRide = new JRadioButton("Override");
		overRide.setEnabled(false);
		txtField1 = new JTextField(40);

		bGroup.add(repGrossEarnings);
		bGroup.add(repMilesDriven);
		bGroup.add(repNetEarnings);
		bGroup.add(reset);
		bGroup.add(overRide);

		add(txtField1);
		add(repMilesDriven);
		add(repGrossEarnings);
		add(repNetEarnings);
		add(reset);
		add(overRide);
		add(okBtn);
		okBtn.addActionListener(this);
		repMilesDriven.addItemListener(this);
		repGrossEarnings.addItemListener(this);
		repNetEarnings.addItemListener(this);
		reset.addItemListener(this);
		txtField1.setText(String.valueOf(0));
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == okBtn) {
			if (repMilesDriven.isSelected()) {
				txtField1.setText(String.valueOf(cab.getTotalMiles()));
			} else if (repGrossEarnings.isSelected()) {
				txtField1.setText(String.valueOf(cab.getGrossEarnings()));
			} else if (repNetEarnings.isSelected()) {
				cab.repNetEarns();
				txtField1.setText(String.valueOf(cab.getNetEarns()));
			} else if (reset.isSelected()) {
				cab.reset();
				txtField1.setText("Earnings and Mileage have been reset!");
			} else if (overRide.isSelected()) {
				cab.overRide();
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
	}

	@Override
	public void maintenanceAlert(int i) {
		if (i == MaintenanceListener.SERVICE_NEEDED) {
			txtField1.setText("Service Needed");
			overRide.setEnabled(true);
		} else if (i == MaintenanceListener.SERVICED) {
			txtField1.setText(String.valueOf(0));
			overRide.setEnabled(false);
		}
	}
}

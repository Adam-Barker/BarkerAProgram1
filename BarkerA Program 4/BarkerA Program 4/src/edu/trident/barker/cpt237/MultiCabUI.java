package edu.trident.barker.cpt237;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;

import javax.swing.*;

public class MultiCabUI implements ActionListener, ItemListener {

	private JTextField gasAvailOut = new JTextField(12);
	private JTextField milesAvailOut = new JTextField(12);
	private JTextField milesSinceServiceOut = new JTextField(12);
	private JTextField milesSinceResetOut = new JTextField(12);
	private JTextField grossSinceResetOut = new JTextField(12);
	private JTextField netSinceResetOut = new JTextField(12);

	private JTextField amountInput = new JTextField(12);
	private JTextField gasPriceInput = new JTextField(12);
	private JTextField output = new JTextField(12);
	private JButton okBtn = new JButton("OK");
	private JButton refreshBtn = new JButton("Refresh");

	private JComboBox<String> cabCombo;
	private JComboBox<String> actionCombo;
	private String[] cabNames;

	Cab2 cab;
	Controller controller;

	public MultiCabUI(String[] cabNames, Controller controller) {
		this.cabNames = cabNames;
		this.controller = controller;
		init();
	}

	private void init() {
		JLabel gasAvail = new JLabel("Gas Available");
		JLabel milesAvail = new JLabel("Miles Available");
		JLabel milesSinceService = new JLabel("Miles since service");
		JLabel milesSinceReset = new JLabel("Miles since reset");
		JLabel grossSinceReset = new JLabel("Gross earnings since reset");
		JLabel netSinceReset = new JLabel("Net earnings since reset");
		JFrame frame = new JFrame("Acme Cabs");
		JPanel cabControlPanel = new JPanel();
		Container contentPane = frame.getContentPane();
		String[] actionNames = { "Record Trip", "Add Gas", "Service", "Reset" };
		Arrays.sort(cabNames);
		
		cabCombo = new JComboBox<String>(cabNames);
		actionCombo = new JComboBox<String>(actionNames);

		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(BorderFactory.createTitledBorder("Cab Info"));
		statusPanel.setLayout(new GridLayout(0, 2, 5, 5));
		statusPanel.add(cabCombo);
		statusPanel.add(refreshBtn);

		// disable all of the output fields
		// western panel
		gasAvailOut.setEditable(false);
		milesAvailOut.setEditable(false);
		milesSinceServiceOut.setEditable(false);
		milesSinceResetOut.setEditable(false);
		grossSinceResetOut.setEditable(false);
		netSinceResetOut.setEditable(false);

		// eastern panel
		amountInput.setEditable(true);
		gasPriceInput.setEditable(false);
		output.setEditable(false);

		// add the labels and output fields in pairs
		statusPanel.add(gasAvail);
		statusPanel.add(gasAvailOut);

		statusPanel.add(milesAvail);
		statusPanel.add(milesAvailOut);

		statusPanel.add(milesSinceService);
		statusPanel.add(milesSinceServiceOut);

		statusPanel.add(milesSinceReset);
		statusPanel.add(milesSinceResetOut);

		statusPanel.add(grossSinceReset);
		statusPanel.add(grossSinceResetOut);

		statusPanel.add(netSinceReset);
		statusPanel.add(netSinceResetOut);

		// construct the second panel, place it in a secondary panel to
		// allow better sized fields
		contentPane.add(statusPanel, BorderLayout.WEST);
		JPanel p1 = new JPanel(); // dummy panel
		contentPane.add(p1, BorderLayout.EAST);
		p1.setBorder(BorderFactory.createTitledBorder("Cab Control"));
		p1.add(cabControlPanel);

		cabControlPanel.setLayout(new GridLayout(0, 1, 5, 5));
		cabControlPanel.add(actionCombo);
		cabControlPanel.add(amountInput);
		cabControlPanel.add(gasPriceInput);
		cabControlPanel.add(output);
		JSeparator js = new JSeparator();
		js.setVisible(false);
		cabControlPanel.add(js);
		cabControlPanel.add(okBtn);

		okBtn.addActionListener(this);
		refreshBtn.addActionListener(this);
		cabCombo.addItemListener(this);
		actionCombo.addItemListener(this);

		// contentPane.add(cabControlPanel, BorderLayout.EAST);

		frame.pack();
		frame.setVisible(true);

	}

	public void setCab(int index) {
	 String name = cabNames[index];
	 cab = controller.loadCab(name);
	 
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		int source = ItemEvent.SELECTED;
		int index = actionCombo.getSelectedIndex();
		if(source == ItemEvent.SELECTED){
			amountInput.setText(null);
			gasPriceInput.setText(null);
			output.setText(null);
		}
		switch (index) {
		case 0:
			amountInput.setEditable(true);
			gasPriceInput.setEditable(false); 
			break;
		case 1:
			amountInput.setEditable(true);
			gasPriceInput.setEditable(true);
			break;
		default:
			amountInput.setEditable(false);
			gasPriceInput.setEditable(false);
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		int cabIndex = cabCombo.getSelectedIndex();
		if (source == refreshBtn) {
			setCab(cabIndex);
			gasAvailOut.setText(String.valueOf(cab.getGasAvailable()));
			milesAvailOut.setText(String.valueOf(cab.getMilesAvailable()));
			milesSinceServiceOut.setText(String.valueOf(cab.getServiceMiles()));
			milesSinceResetOut.setText(String.valueOf(cab.getMiles()));
			grossSinceResetOut.setText(String.valueOf(cab.getGrossEarnings()));
			netSinceResetOut.setText(String.valueOf(cab.getNetEarns()));
		}

		if (source == okBtn) {
			int index = actionCombo.getSelectedIndex();
			String input1 = amountInput.getText();
			String input2 = gasPriceInput.getText();
			switch (index) {
			case 0:
				try {
					double num1 = Double.parseDouble(input1);
					if (index == 0) {
						double miles = num1;
						if (cab.getMilesAvailable() > miles)
							cab.trip(miles);
						else
							output.setText("Not Enough Gas!");
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null,
							"That is an invalid number!");
				}
				break;
			case 1:
				try {
					double num1 = Double.parseDouble(input1);
					double num2 = Double.parseDouble(input2);
					double gasInput = num1;
					double gasPrice = num2;
					cab.addGas(gasInput, gasPrice);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null,
							"That is an invalid number!");
				}
				break;
			case 2:
				cab.service();
				output.setText("Cab has been serviced!");
				break;
			case 3:
				cab.reset();
				output.setText("Cab has been reset!");
				break;

			}
		}

	}
}
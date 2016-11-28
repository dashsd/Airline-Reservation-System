package com.napier.airlinereservation.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.napier.airlinereservation.datatypes.Passenger;
import com.napier.airlinereservation.datatypes.Passenger.PASSENGER_CLASS;
import com.napier.airlinereservation.helpers.DataHelper.DataType;
import com.napier.airlinereservation.helpers.DataHelper.OpType;
import com.napier.airlinereservation.helpers.DataHelper;
import com.napier.airlinereservation.helpers.IObserver;

public class PassengerGUI extends JPanel implements ActionListener, IObserver<Passenger> {

	private static final long serialVersionUID = -8762527504926439254L;
	private JPanel formPanel;
	private JTable tablePassenger;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private Box mainBox;

	private JLabel lblPassengerID;
	private JLabel lblPassengerName;
	private JLabel lblPassengerAge;
	private JLabel lblPassengerAddress;
	private JLabel lblPassengerClass;

	private JTextField txtPassengerID;
	private JTextField txtPassengerName;
	private JTextField txtPassengerAge;
	private JTextField txtPassengerAddress;
	private JComboBox<String> cmbPassengerClass;

	private JButton btnSavePassenger;
	private JButton btnDeletePassenger;

	private int DEFAULT_WIDTH = 400;
	private int DEFAULT_HEIGHT = 40;

	private JPanel idPanel;
	private JPanel namePanel;
	private JPanel agePanel;
	private JPanel addressPanel;
	private JPanel classPanel;
	private JPanel flightPanel;
	private JPanel buttonPanel;

	private Passenger passenger = null;

	/**
	 * Create the panel.
	 */
	public PassengerGUI() {
		buildGUI();
	}

	private void buildGUI() {
		setLayout(new GridLayout(1, 2));
		drawForm();
		initializeTable();
		add(formPanel);
		add(scrollPane);
	}

	private void drawForm() {
		formPanel = new JPanel();
		formPanel.setBorder(BorderFactory.createTitledBorder("Passengers Form"));
		formPanel.setLayout(new BorderLayout());

		lblPassengerID = new JLabel("Passenger ID");
		lblPassengerName = new JLabel("Name");
		lblPassengerAge = new JLabel("Age");
		lblPassengerAddress = new JLabel("Address");
		lblPassengerClass = new JLabel("Seat Type");

		txtPassengerID = new JTextField(10);
		txtPassengerName = new JTextField(15);
		txtPassengerAge = new JTextField(5);
		txtPassengerAddress = new JTextField(15);

		cmbPassengerClass = new JComboBox<>();
		cmbPassengerClass.addItem("Standard");
		cmbPassengerClass.addItem("Business");

		btnSavePassenger = new JButton("Add new");
		btnSavePassenger.addActionListener(this);
		btnDeletePassenger = new JButton("Delete");
		btnDeletePassenger.addActionListener(this);

		idPanel = new JPanel();
		namePanel = new JPanel();
		agePanel = new JPanel();
		addressPanel = new JPanel();
		classPanel = new JPanel();
		flightPanel = new JPanel();
		buttonPanel = new JPanel();

		idPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		idPanel.setMaximumSize(idPanel.getPreferredSize());
		idPanel.setLayout(new FlowLayout());
		idPanel.add(lblPassengerID);
		idPanel.add(txtPassengerID);

		namePanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		namePanel.setMaximumSize(namePanel.getPreferredSize());
		namePanel.setLayout(new FlowLayout());
		namePanel.add(lblPassengerName);
		namePanel.add(txtPassengerName);

		agePanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		agePanel.setMaximumSize(agePanel.getPreferredSize());
		agePanel.setLayout(new FlowLayout());
		agePanel.add(lblPassengerAge);
		agePanel.add(txtPassengerAge);

		addressPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		addressPanel.setMaximumSize(addressPanel.getPreferredSize());
		addressPanel.setLayout(new FlowLayout());
		addressPanel.add(lblPassengerAddress);
		addressPanel.add(txtPassengerAddress);

		classPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		classPanel.setMaximumSize(classPanel.getPreferredSize());
		classPanel.setLayout(new FlowLayout());
		classPanel.add(lblPassengerClass);
		classPanel.add(cmbPassengerClass);

		buttonPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		buttonPanel.setMaximumSize(buttonPanel.getPreferredSize());
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(btnSavePassenger);
		buttonPanel.add(btnDeletePassenger);

		mainBox = Box.createVerticalBox();
		mainBox.add(idPanel);
		mainBox.add(namePanel);
		mainBox.add(agePanel);
		mainBox.add(addressPanel);
		mainBox.add(classPanel);
		mainBox.add(flightPanel);
		mainBox.add(buttonPanel);

		formPanel.add(mainBox, BorderLayout.CENTER);
	}

	private void initializeTable() {
		Object columnNames[] = { "Passenger ID", "Name", "Age", "Address", "Seat Type" };
		tableModel = new DefaultTableModel(columnNames, 0);
		tablePassenger = new JTable(tableModel);
		scrollPane = new JScrollPane(tablePassenger);
		setUpTableData();
	}

	private void setUpTableData() {
		tableModel = (DefaultTableModel) tablePassenger.getModel();
		tableModel.setRowCount(0);

		LinkedHashMap<String, Passenger> passengerMap = DataHelper.Instance.getList(DataType.PASSENGER);
		Iterator<Passenger> iterator = passengerMap.values().iterator();
		while (iterator.hasNext()) {
			Passenger passenger = iterator.next();
			Vector<Object> vector = returnObjectArray(passenger);
			tableModel.addRow(vector);
		}
		tablePassenger.repaint();
	}

	private Vector<Object> returnObjectArray(Passenger passenger2) {
		Vector<Object> vector = new Vector<>();
		vector.add(passenger2.getPassengerID());
		vector.add(passenger2.getPersonName());
		vector.add(passenger2.getPersonAge());
		vector.add(passenger2.getPersonAddress());
		vector.add(passenger2.getPassengerClass());
		return vector;
	}

	@Override
	public void notify(Passenger model, OpType opType) {
		setUpTableData();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ageText = txtPassengerAge.getText();
		int age = 0;
		try {
			age = Integer.parseInt(ageText);
		} catch (NumberFormatException e1) {
			showError("Invalid Age. Only numbers allowed.");
			return;
		}

		int selectedClassIndex = cmbPassengerClass.getSelectedIndex();
		PASSENGER_CLASS pClass = null;
		switch (selectedClassIndex) {
		case 0:
			pClass = PASSENGER_CLASS.STANDARD;
			break;
		case 1:
			pClass = PASSENGER_CLASS.BUSINESS;
			break;
		default:
			JOptionPane.showMessageDialog(null, "Select a passenger class", "Error", JOptionPane.INFORMATION_MESSAGE);
			break;
		}

		passenger = new Passenger(txtPassengerName.getText(), age, txtPassengerAddress.getText(),
				txtPassengerID.getText());
		passenger.setPassengerClass(pClass);
		passenger.addObserver(this);

		if (e.getSource() == btnSavePassenger) {
			String result = validateControls(DataHelper.OpType.INSERT);
			if (result != null) {
				showError(result);
				return;
			} else {
				boolean bookingPossible = true;
				if (bookingPossible) {
					saveRecord(OpType.INSERT);
				} else {
					showError("Sorry. You have crossed maximum number of bookings available");
				}
				// return;
			}
		}

		if (e.getSource() == btnDeletePassenger) {
			String result = validateControls(DataHelper.OpType.DELETE);
			if (result != null) {
				showError(result);
				return;
			} else {
				deleteRecord();
				return;
			}
		}
	}

	public void saveRecord(OpType opType) {
		boolean isSaved = DataHelper.Instance.addObject(passenger, passenger.getPassengerID(), DataType.PASSENGER,
				opType);
		if (isSaved) {
			JOptionPane.showMessageDialog(formPanel, "Record Saved", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
			clearForm();
		} else {
			JOptionPane.showMessageDialog(formPanel, "Unable to save the record", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void deleteRecord() {
		boolean isRemoved = DataHelper.Instance.removeObject(passenger.getPassengerID(), DataType.PASSENGER);
		if (isRemoved) {
			JOptionPane.showMessageDialog(formPanel, "Record Deleted", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
			clearForm();
		} else {
			JOptionPane.showMessageDialog(formPanel, "Unable to delete the record", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void clearForm() {
		txtPassengerID.setText(null);
		txtPassengerName.setText(null);
		txtPassengerAge.setText(null);
		txtPassengerAddress.setText(null);
		cmbPassengerClass.setSelectedIndex(0);
	}

	private void showError(String message) {
		JOptionPane.showMessageDialog(formPanel, message, "Validation Error", JOptionPane.ERROR_MESSAGE);
	}

	public String validateControls(OpType opType) {
		String passengerID = passenger.getPassengerID();
		String passengerName = passenger.getPersonName();
		int passengerAge = passenger.getPersonAge();
		String passengerAddress = passenger.getPersonAddress();
		PASSENGER_CLASS passengerClass = passenger.getPassengerClass();

		if (passengerID.length() <= 0) {
			return "Passenger ID can not be blank.";
		}

		if (passengerName.length() <= 0 && opType != OpType.DELETE) {
			return "Passenger Name can not be blank.";
		}

		if (passengerAge < 1 && opType != OpType.DELETE) {
			return "Invalid Passenger Age";
		}

		if (passengerAddress.length() <= 0 && opType != OpType.DELETE) {
			return "Passenger Address can not be blank";
		}

		if (passengerClass.toString().length() <= 0) {
			return "Passenger Class can not be blank";
		}

		boolean exists = DataHelper.Instance.containsKey(passengerID, DataHelper.DataType.PASSENGER);
		if (exists && opType == OpType.INSERT) {
			return "Passenger already exists in the system. No duplicates allowed";
		}

		if (!exists && opType == OpType.DELETE) {
			return "Sorry. No Passenger Record in the System.";
		}

		return null;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}

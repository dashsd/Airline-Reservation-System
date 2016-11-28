package com.napier.airlinereservation.gui;

import java.awt.BorderLayout;
import java.awt.Component;
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

import com.napier.airlinereservation.datatypes.Airline;
import com.napier.airlinereservation.datatypes.Flight;
import com.napier.airlinereservation.helpers.DataHelper;
import com.napier.airlinereservation.helpers.DataHelper.DataType;
import com.napier.airlinereservation.helpers.DataHelper.OpType;
import com.napier.airlinereservation.helpers.IObserver;

public class FlightGUI extends JPanel implements ActionListener, IObserver<Flight> {

	private static final long serialVersionUID = 2892904376555547152L;
	private JPanel formPanel;
	private JTable tableFlight;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private Box mainBox;

	private JLabel lblFlightID;
	private JLabel lblFlightOrigin;
	private JLabel lblFlightDestination;
	private JLabel lblFlightTakeOffTime;
	private JLabel lblFlightLandingTime;
	private JLabel lblAirline;

	private JTextField txtFlightID;
	private JTextField txtFlightOrigin;
	private JTextField txtFlightDestination;
	private JTextField txtFlightTakeOffTime;
	private JTextField txtFlightLandingTime;
	private JComboBox<String> cmbAirline;

	private JButton btnSaveFlight;

	private int DEFAULT_WIDTH = 400;
	private int DEFAULT_HEIGHT = 40;

	private JPanel idPanel;
	private JPanel originPanel;
	private JPanel destinationPanel;
	private JPanel takeOffPanel;
	private JPanel landingPanel;
	private JPanel airlinePanel;
	private JPanel buttonPanel;

	private Flight flight = null;

	/**
	 * Create the panel.
	 */
	public FlightGUI() {
		buildGUI();
	}

	private void buildGUI() {
		setLayout(new GridLayout(1, 2));
		drawForm();
		initializeTable();
		add(formPanel);
		add(scrollPane);
//		setSize(850, 400);
	}

	private void drawForm() {
		formPanel = new JPanel();
		formPanel.setBorder(BorderFactory.createTitledBorder("Flight Form"));
		formPanel.setLayout(new BorderLayout());

		lblFlightID = new JLabel("Flight ID");
		lblFlightOrigin = new JLabel("Flight Origin");
		lblFlightDestination = new JLabel("Flight Destination");
		lblFlightTakeOffTime = new JLabel("Take Off Time");
		lblFlightLandingTime = new JLabel("Landing Time");
		lblAirline = new JLabel("Airline");

		txtFlightID = new JTextField(10);
		txtFlightOrigin = new JTextField(10);
		txtFlightDestination = new JTextField(10);
		txtFlightTakeOffTime = new JTextField(10);
		txtFlightLandingTime = new JTextField(10);

		cmbAirline = new JComboBox<>();
		updateComboBox();

		btnSaveFlight = new JButton("Add new");
		btnSaveFlight.addActionListener(this);

		idPanel = new JPanel();
		originPanel = new JPanel();
		destinationPanel = new JPanel();
		takeOffPanel = new JPanel();
		landingPanel = new JPanel();
		airlinePanel = new JPanel();
		buttonPanel = new JPanel();

		idPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		idPanel.setMaximumSize(idPanel.getPreferredSize());
		idPanel.setLayout(new FlowLayout());
		idPanel.add(lblFlightID);
		idPanel.add(txtFlightID);

		originPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		originPanel.setMaximumSize(originPanel.getPreferredSize());
		originPanel.setLayout(new FlowLayout());
		originPanel.add(lblFlightOrigin);
		originPanel.add(txtFlightOrigin);

		destinationPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		destinationPanel.setMaximumSize(destinationPanel.getPreferredSize());
		destinationPanel.setLayout(new FlowLayout());
		destinationPanel.add(lblFlightDestination);
		destinationPanel.add(txtFlightDestination);

		takeOffPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		takeOffPanel.setMaximumSize(takeOffPanel.getPreferredSize());
		takeOffPanel.setLayout(new FlowLayout());
		takeOffPanel.add(lblFlightTakeOffTime);
		takeOffPanel.add(txtFlightTakeOffTime);

		landingPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		landingPanel.setMaximumSize(landingPanel.getPreferredSize());
		landingPanel.setLayout(new FlowLayout());
		landingPanel.add(lblFlightLandingTime);
		landingPanel.add(txtFlightLandingTime);

		airlinePanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		airlinePanel.setMaximumSize(airlinePanel.getPreferredSize());
		airlinePanel.setLayout(new FlowLayout());
		airlinePanel.add(lblAirline);
		airlinePanel.add(cmbAirline);

		buttonPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		buttonPanel.setMaximumSize(buttonPanel.getPreferredSize());
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(btnSaveFlight);
		buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		mainBox = Box.createVerticalBox();
		mainBox.add(idPanel);
		mainBox.add(originPanel);
		mainBox.add(destinationPanel);
		mainBox.add(takeOffPanel);
		mainBox.add(landingPanel);
		mainBox.add(airlinePanel);
		mainBox.add(buttonPanel);

		formPanel.add(mainBox, BorderLayout.CENTER);

	}

	private void updateComboBox() {
		if (cmbAirline == null) {
			cmbAirline = new JComboBox<>();
		}

		LinkedHashMap<String, Airline> airlineMap = DataHelper.Instance.getList(DataType.AIRLINE);
		cmbAirline.removeAllItems();
		Iterator<Airline> iterator = airlineMap.values().iterator();
		while (iterator.hasNext()) {
			Airline airline = iterator.next();
			cmbAirline.addItem(airline.getAirlineName());
		}
	}

	private void initializeTable() {
		Object columnNames[] = { "Flight ID", "Origin", "Destination", "Take Off Time", "Landing Time", "Airline" };
		tableModel = new DefaultTableModel(columnNames, 0);
		tableFlight = new JTable(tableModel);
		scrollPane = new JScrollPane(tableFlight);
		setUpTableData();
	}

	private void setUpTableData() {
		tableModel = (DefaultTableModel) tableFlight.getModel();
		tableModel.setRowCount(0);

		LinkedHashMap<String, Flight> flightMap = DataHelper.Instance.getList(DataType.FLIGHT);
		Iterator<Flight> iterator = flightMap.values().iterator();
		while (iterator.hasNext()) {
			Flight flight = iterator.next();
			Vector<Object> vector = returnObjectArray(flight);
			tableModel.addRow(vector);
		}
		tableFlight.repaint();
	}

	private Vector<Object> returnObjectArray(Flight flight2) {
		Vector<Object> vector = new Vector<>();
		vector.add(flight2.getFlightID());
		vector.add(flight2.getFlightOrigin());
		vector.add(flight2.getFlightDestination());
		vector.add(flight2.getFlightTakeOffTime());
		vector.add(flight2.getFlightLandingTime());
		vector.add(flight2.getAirline().getAirlineName());
		return vector;
	}

	@Override
	public void notify(Flight model, OpType opType) {
		setUpTableData();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		flight = new Flight();
		flight.setFlightID(txtFlightID.getText());
		flight.setFlightOrigin(txtFlightOrigin.getText());
		flight.setFlightDestination(txtFlightDestination.getText());
		flight.setFlightTakeOffTime(txtFlightTakeOffTime.getText());
		flight.setFlightLandingTime(txtFlightLandingTime.getText());
		int selectedAirlineIndex = cmbAirline.getSelectedIndex();
		Airline airline = (Airline) DataHelper.Instance.getObjectByIndex(selectedAirlineIndex, DataType.AIRLINE);
		flight.setAirline(airline);
		flight.addObserver(this);

		if (e.getSource() == btnSaveFlight) {
			String result = validateControls(DataHelper.OpType.INSERT);
			if (result != null) {
				showError(result);
				return;
			} else {
				saveRecord(OpType.INSERT);
				return;
			}
		}
	}

	public void saveRecord(OpType opType) {
		boolean isSaved = DataHelper.Instance.addObject(flight, flight.getFlightID(), DataType.FLIGHT, opType);
		if (isSaved) {
			JOptionPane.showMessageDialog(formPanel, "Record Saved", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
			clearForm();
		} else {
			JOptionPane.showMessageDialog(formPanel, "Unable to save the record", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void showError(String message) {
		JOptionPane.showMessageDialog(formPanel, message, "Validation Error", JOptionPane.ERROR_MESSAGE);

	}

	public String validateControls(OpType opType) {
		String flightID = flight.getFlightID();
		String flightOrigin = flight.getFlightOrigin();
		String flightDestination = flight.getFlightDestination();
		String flightTakeOffTime = flight.getFlightTakeOffTime();
		String flightLandingTime = flight.getFlightLandingTime();
		Airline flightAirlineCode = flight.getAirline();

		if (flightID.length() <= 0) {
			return "Flight ID can not be blank.";
		}

		if (flightOrigin.length() <= 0) {
			return "Flight Origin can not be blank.";
		}

		if (flightDestination.length() <= 0) {
			return "Flight Destination can not be blank.";
		}

		if (flightTakeOffTime.length() <= 0) {
			return "Flight Take Off Time can not be blank.";
		}

		if (flightLandingTime.length() <= 0) {
			return "Flight Landing Time can not be blank.";
		}

		if (flightAirlineCode.getAirlineCode().length() <= 0) {
			return "Airline Code can not be blank.";
		}

		boolean exists = DataHelper.Instance.containsKey(flightID, DataHelper.DataType.FLIGHT);
		if (exists && opType == OpType.INSERT) {
			return "Flight already exists in the system. No duplicates allowed";
		}
		return null;
	}

	private void clearForm() {
		txtFlightID.setText(null);
		txtFlightOrigin.setText(null);
		txtFlightDestination.setText(null);
		txtFlightTakeOffTime.setText(null);
		txtFlightLandingTime.setText(null);
		cmbAirline.setSelectedIndex(0);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		updateComboBox();
	}

}

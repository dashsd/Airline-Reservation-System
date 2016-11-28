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
import javax.swing.table.DefaultTableModel;

import com.napier.airlinereservation.datatypes.Flight;
import com.napier.airlinereservation.datatypes.Passenger;
import com.napier.airlinereservation.datatypes.PassengerBooking;
import com.napier.airlinereservation.helpers.DataHelper.DataType;
import com.napier.airlinereservation.helpers.DataHelper.OpType;
import com.napier.airlinereservation.helpers.BookingHelper;
import com.napier.airlinereservation.helpers.DataHelper;
import com.napier.airlinereservation.helpers.IObserver;

public class BookingGUI extends JPanel implements ActionListener, IObserver<PassengerBooking> {

	private static final long serialVersionUID = 2654771029234438507L;
	private JPanel formPanel;
	private JTable tableBooking;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private Box mainBox;

	private JLabel lblFlight;
	private JLabel lblPassenger;

	private JComboBox<String> cmbPassenger;
	private JComboBox<String> cmbFlight;

	private JButton btnSaveBook;
	private JButton btnDeleteBook;

	private int DEFAULT_WIDTH = 400;
	private int DEFAULT_HEIGHT = 40;

	private JPanel flightPanel;
	private JPanel passengerPanel;
	private JPanel buttonPanel;

	private PassengerBooking passengerBooking = null;

	/**
	 * Create the panel.
	 */
	public BookingGUI() {
		buildGUI();
	}

	private void buildGUI() {
		setLayout(new GridLayout(1, 2));
		drawForm();
		initializeTable();
		add(formPanel);
		add(scrollPane);
		setVisible(true);
	}

	private void drawForm() {
		formPanel = new JPanel();
		formPanel.setBorder(BorderFactory.createTitledBorder("Booking Form"));
		formPanel.setLayout(new BorderLayout());

		lblFlight = new JLabel("Flight");
		lblPassenger = new JLabel("Passenger");

		cmbFlight = new JComboBox<>();
		cmbPassenger = new JComboBox<>();
		updateComboBox();

		btnSaveBook = new JButton("Book");
		btnSaveBook.addActionListener(this);
		btnDeleteBook = new JButton("Delete");
		btnDeleteBook.addActionListener(this);

		flightPanel = new JPanel();
		passengerPanel = new JPanel();
		buttonPanel = new JPanel();

		flightPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		flightPanel.setMaximumSize(flightPanel.getPreferredSize());
		flightPanel.setLayout(new FlowLayout());
		flightPanel.add(lblFlight);
		flightPanel.add(cmbFlight);

		passengerPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		passengerPanel.setMaximumSize(passengerPanel.getPreferredSize());
		passengerPanel.setLayout(new FlowLayout());
		passengerPanel.add(lblPassenger);
		passengerPanel.add(cmbPassenger);

		buttonPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		buttonPanel.setMaximumSize(buttonPanel.getPreferredSize());
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(btnSaveBook);
		buttonPanel.add(btnDeleteBook);

		mainBox = Box.createVerticalBox();
		mainBox.add(flightPanel);
		mainBox.add(passengerPanel);
		mainBox.add(buttonPanel);

		formPanel.add(mainBox, BorderLayout.CENTER);
	}

	private void updateComboBox() {
		if (cmbFlight == null) {
			cmbFlight = new JComboBox<>();
		}
		LinkedHashMap<String, Flight> flightMap = DataHelper.Instance.getList(DataType.FLIGHT);
		cmbFlight.removeAllItems();
		Iterator<Flight> flightIterator = flightMap.values().iterator();
		while (flightIterator.hasNext()) {
			Flight flight = flightIterator.next();
			cmbFlight.addItem(flight.getFlightID());
		}

		if (cmbPassenger == null) {
			cmbPassenger = new JComboBox<>();
		}
		LinkedHashMap<String, Passenger> passengerMap = DataHelper.Instance.getList(DataType.PASSENGER);
		cmbPassenger.removeAllItems();
		Iterator<Passenger> passengerIterator = passengerMap.values().iterator();
		while (passengerIterator.hasNext()) {
			Passenger passenger = passengerIterator.next();
			cmbPassenger.addItem(passenger.getPassengerID());
		}
	}

	private void initializeTable() {
		Object columnNames[] = { "Passenger ID", "Passenger", "Flight ID" };
		tableModel = new DefaultTableModel(columnNames, 0);
		tableBooking = new JTable(tableModel);
		scrollPane = new JScrollPane(tableBooking);
		setUpTableData();
	}

	private void setUpTableData() {
		tableModel = (DefaultTableModel) tableBooking.getModel();
		tableModel.setRowCount(0);

		LinkedHashMap<String, PassengerBooking> bookingMap = DataHelper.Instance.getList(DataType.PASSENGER_BOOKING);
		Iterator<PassengerBooking> iterator = bookingMap.values().iterator();
		while (iterator.hasNext()) {
			PassengerBooking passengerBooking = iterator.next();
			Vector<Object> vector = returnObjectArray(passengerBooking);
			tableModel.addRow(vector);
		}
		tableBooking.repaint();
	}

	private Vector<Object> returnObjectArray(PassengerBooking passengerBooking2) {
		Vector<Object> vector = new Vector<>();
		vector.add(passengerBooking2.getPassenger().getPassengerID());
		vector.add(passengerBooking2.getPassenger().getPersonName());
		vector.add(passengerBooking2.getFlight().getFlightID());
		return vector;
	}

	@Override
	public void notify(PassengerBooking model, OpType opType) {
		setUpTableData();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		passengerBooking = new PassengerBooking();

		int selectedFlightIndex = cmbFlight.getSelectedIndex();
		Flight flight = (Flight) DataHelper.Instance.getObjectByIndex(selectedFlightIndex, DataType.FLIGHT);
		passengerBooking.setFlight(flight);

		int selectedPassengerIndex = cmbPassenger.getSelectedIndex();
		Passenger passenger = (Passenger) DataHelper.Instance.getObjectByIndex(selectedPassengerIndex,
				DataType.PASSENGER);
		passengerBooking.setPassenger(passenger);

		passengerBooking.addObserver(this);

		if (e.getSource() == btnSaveBook) {
			String result = validateControls(DataHelper.OpType.INSERT);
			if (result != null) {
				showError(result);
				return;
			} else {
				boolean bookingPossible = BookingHelper.bookingResolver(passengerBooking);
				if (bookingPossible) {
					saveRecord(OpType.INSERT);
				} else {
					showError("Sorry. You have crossed the maximum number of bookings allowed");
				}
				return;
			}
		}

		if (e.getSource() == btnDeleteBook) {
			String result = validateControls(DataHelper.OpType.DELETE);
			if (result != null) {
				showError(result);
				return;
			} else {
				deleteRecord();
				setUpTableData();
				return;
			}
		}
	}

	private void saveRecord(OpType opType) {
		String key = passengerBooking.getPassenger().getPassengerID() + ":"
				+ passengerBooking.getFlight().getFlightID();
		boolean isSaved = DataHelper.Instance.addObject(passengerBooking, key, DataType.PASSENGER_BOOKING, opType);
		if (isSaved) {
			JOptionPane.showMessageDialog(formPanel, "Record Saved", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
			clearForm();
		} else {
			JOptionPane.showMessageDialog(formPanel, "Unable to save the record", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void deleteRecord() {
		String key = passengerBooking.getPassenger().getPassengerID() + ":"
				+ passengerBooking.getFlight().getFlightID();
		boolean isRemoved = DataHelper.Instance.removeObject(key, DataType.PASSENGER_BOOKING);
		if (isRemoved) {
			JOptionPane.showMessageDialog(formPanel, "Record Deleted", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
			clearForm();
		} else {
			JOptionPane.showMessageDialog(formPanel, "Unable to delete the record", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void showError(String message) {
		JOptionPane.showMessageDialog(formPanel, message, "Validation Error", JOptionPane.ERROR_MESSAGE);
	}

	private String validateControls(OpType opType) {
		String flightID = passengerBooking.getFlight().getFlightID();
		String passengerID = passengerBooking.getPassenger().getPassengerID();

		if (flightID.length() <= 0) {
			return "Flight ID can not be blank";
		}

		if (passengerID.length() <= 0) {
			return "Passenger ID can not be blank.";
		}

		String key = passengerID + ":" + flightID;
		boolean exists = DataHelper.Instance.containsKey(key, DataHelper.DataType.PASSENGER_BOOKING);
		if (exists && opType == OpType.INSERT) {
			return "Passenger already booked for this flight";
		}

		if (!exists && opType == OpType.DELETE) {
			return "Sorry. Booking record for this passenger does not exist in the system.";
		}

		return null;
	}

	private void clearForm() {
		cmbFlight.setSelectedIndex(0);
		cmbPassenger.setSelectedIndex(0);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		updateComboBox();
	}

}

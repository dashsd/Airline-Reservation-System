package com.napier.airlinereservation.gui;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.napier.airlinereservation.helpers.FileHelper;

public class ReservationWindowGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 7015622559546720214L;

	private JMenuBar menuBar;
	private JMenu mnCRUD;
	private JMenu mnBook;
	private JMenu mnStore;

	private JMenuItem mntmAirline;
	private JMenuItem mntmFlight;
	private JMenuItem mntmPassenger;
	private JMenuItem mntmBookPassenger;
	private JMenuItem mntmStoreData;

	protected AirlineGUI airlinePanel;
	protected FlightGUI flightPanel;
	protected PassengerGUI passengerPanel;
	protected BookingGUI bookingPanel;

	private Container pane = this.getContentPane();
	private CardLayout layout = new CardLayout();

	/**
	 * Create the frame.
	 */
	public ReservationWindowGUI() {
		setResizable(false);
		setTitle("Airline Reservation System");
		setSize(850, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(layout);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnCRUD = new JMenu("Data");
		mnCRUD.setMnemonic('D');
		menuBar.add(mnCRUD);

		mnBook = new JMenu("Book");
		mnBook.setMnemonic('B');
		menuBar.add(mnBook);

		mnStore = new JMenu("Store");
		mnStore.setMnemonic('S');
		menuBar.add(mnStore);

		mntmAirline = new JMenuItem("Airlines");
		mntmAirline.setMnemonic('A');
		mntmAirline.addActionListener(this);
		mnCRUD.add(mntmAirline);

		mntmFlight = new JMenuItem("Flights");
		mntmFlight.setMnemonic('F');
		mntmFlight.addActionListener(this);
		mnCRUD.add(mntmFlight);

		mntmPassenger = new JMenuItem("Passengers");
		mntmPassenger.setMnemonic('P');
		mntmPassenger.addActionListener(this);
		mnCRUD.add(mntmPassenger);

		mntmBookPassenger = new JMenuItem("Book Passenger");
		mntmBookPassenger.setMnemonic('O');
		mntmBookPassenger.addActionListener(this);
		mnBook.add(mntmBookPassenger);

		mntmStoreData = new JMenuItem("Store Data");
		mntmStoreData.setMnemonic('T');
		mntmStoreData.addActionListener(this);
		mnStore.add(mntmStoreData);

		airlinePanel = new AirlineGUI();
		flightPanel = new FlightGUI();
		passengerPanel = new PassengerGUI();
		bookingPanel = new BookingGUI();

		pane.add("Airline", airlinePanel);
		pane.add("Flight", flightPanel);
		pane.add("Passenger", passengerPanel);
		pane.add("Booking", bookingPanel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmAirline) {
			layout.show(pane, "Airline");
		} else if (e.getSource() == mntmFlight) {
			layout.show(pane, "Flight");
		} else if (e.getSource() == mntmPassenger) {
			layout.show(pane, "Passenger");
		} else if (e.getSource() == mntmBookPassenger) {
			layout.show(pane, "Booking");
		} else if (e.getSource() == mntmStoreData) {
			boolean result = FileHelper.serialize();
			if (result) {
				JOptionPane.showMessageDialog(null, "Successfully saved", "Success", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Error storing records", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}

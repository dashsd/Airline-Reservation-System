package com.napier.airlinereservation.ui;

import java.awt.HeadlessException;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.swing.JOptionPane;

import com.napier.airlinereservation.datatypes.Airline;
import com.napier.airlinereservation.datatypes.Flight;
import com.napier.airlinereservation.datatypes.Passenger;
import com.napier.airlinereservation.datatypes.Passenger.PASSENGER_CLASS;
import com.napier.airlinereservation.datatypes.PassengerBooking;
import com.napier.airlinereservation.helpers.BookingHelper;
import com.napier.airlinereservation.helpers.DataHelper;
import com.napier.airlinereservation.helpers.DataHelper.DataType;
import com.napier.airlinereservation.helpers.DataHelper.OpType;
import com.napier.airlinereservation.helpers.FileHelper;

public class AirlineReservationService {

	// Add airlines
	public void addAirline(String airlineCode, String airlineName) {
		try {
			airlineCode = JOptionPane.showInputDialog("Enter the Airline Code");
			airlineName = JOptionPane.showInputDialog("Enter the Airline Name");

			while (true) {
				if (!DataHelper.Instance.containsKey(airlineCode, DataType.AIRLINE)) {
					Airline airline = new Airline(airlineCode, airlineName);
					DataHelper.Instance.addObject(airline, airline.getAirlineCode(), DataType.AIRLINE, OpType.INSERT);
					break;
				}
				JOptionPane.showMessageDialog(null, "Airline already exists in the system. No duplicates allowed",
						"ERROR", JOptionPane.ERROR_MESSAGE);
				break;
			}
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
	}

	// Display Airlines
	public void displayAirlineList() {
		LinkedHashMap<String, Airline> airlineMap = DataHelper.Instance.getList(DataType.AIRLINE);
		Iterator<Airline> airlineIterator = airlineMap.values().iterator();
		while (airlineIterator.hasNext()) {
			Airline airline = airlineIterator.next();
			airline.displayDetails();

		}
	}

	// Add Flights
	public void addFlight(String flightID, String origin, String destination, String takeOffTime, String landingTime) {
		try {
			flightID = JOptionPane.showInputDialog("Enter the Flight ID");
			origin = JOptionPane.showInputDialog("Enter the Origin of the Flight");
			destination = JOptionPane.showInputDialog("Enter the Destination of the Flight");
			takeOffTime = JOptionPane.showInputDialog("Enter the Take Off Time");
			landingTime = JOptionPane.showInputDialog("Enter the Landing Time");
			String airlineCode = JOptionPane.showInputDialog("Enter the Airline Code to add the flight to");
			Airline airline = (Airline) DataHelper.Instance.getObject(airlineCode, DataType.AIRLINE);

			while (true) {
				if (!DataHelper.Instance.containsKey(flightID, DataType.FLIGHT)) {
					Flight flight = new Flight(flightID, origin, destination, takeOffTime, landingTime);
					DataHelper.Instance.addObject(flight, flightID, DataType.FLIGHT, OpType.INSERT);
					flight.setAirline(airline);
					break;
				}
				JOptionPane.showMessageDialog(null, "Flight already exists in the system. No duplicates allowed",
						"ERROR", JOptionPane.ERROR_MESSAGE);
				break;
			}
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
	}

	// Display Flights
	public void displayFlightList() {
		LinkedHashMap<String, Flight> flightMap = DataHelper.Instance.getList(DataType.FLIGHT);
		Iterator<Flight> flightIterator = flightMap.values().iterator();
		while (flightIterator.hasNext()) {
			Flight flight = flightIterator.next();
			flight.displayDetails();
		}
	}

	// Add Passengers
	public void addPassenger(String name, int age, String address, String passengerID) {
		try {
			passengerID = JOptionPane.showInputDialog("Enter the Passenger ID");
			name = JOptionPane.showInputDialog("Enter the Passenger's Name");
			age = Integer.parseInt(JOptionPane.showInputDialog("Enter the Passenger's Age"));
			address = JOptionPane.showInputDialog("Enter the Passenger's Address");
			String pClass = JOptionPane.showInputDialog("Select Passenger Class: 1 for Standard and 2 for Business");
			PASSENGER_CLASS passengerClass = null;
			int choice = 0;
			choice = Integer.parseInt(pClass);

			switch (choice) {
			case 1:
				passengerClass = PASSENGER_CLASS.STANDARD;
				break;
			case 2:
				passengerClass = PASSENGER_CLASS.BUSINESS;
				break;
			}

			while (true) {
				if (!DataHelper.Instance.containsKey(passengerID, DataType.PASSENGER)) {
					Passenger passenger = new Passenger(name, age, address, passengerID);
					passenger.setPassengerClass(passengerClass);
					DataHelper.Instance.addObject(passenger, passenger.getPassengerID(), DataType.PASSENGER,
							OpType.INSERT);
					break;
				}
				JOptionPane.showMessageDialog(null, "Passenger already exists in the system. No duplicates allowed",
						"ERROR", JOptionPane.ERROR_MESSAGE);
				break;
			}
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	// Display Passengers
	public void displayPassengerList() {
		LinkedHashMap<String, Passenger> passengerMap = DataHelper.Instance.getList(DataType.PASSENGER);
		Iterator<Passenger> passengerIterator = passengerMap.values().iterator();
		while (passengerIterator.hasNext()) {
			Passenger passenger = passengerIterator.next();
			passenger.displayDetails();
		}
	}

	public void removePassenger() {
		String passengerID = JOptionPane.showInputDialog("Enter the Passenger ID to remove");
		while (true) {
			if (DataHelper.Instance.containsKey(passengerID, DataType.PASSENGER)) {
				boolean isRemoved = DataHelper.Instance.removeObject(passengerID, DataType.PASSENGER);
				if (isRemoved) {
					JOptionPane.showMessageDialog(null, "Passenger Removed", "SUCCESS",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				} else {
					JOptionPane.showMessageDialog(null, "Unable to remove passenger", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					break;
				}
			}
			JOptionPane.showMessageDialog(null, "No passenger record found in the system", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			break;
		}

	}

	// Add Bookings
	public void addPassengerBooking(String passengerID, String flightID) {
		try {
			passengerID = JOptionPane.showInputDialog("Enter the Passenger ID");
			flightID = JOptionPane.showInputDialog("Enter the Flight ID");
			Passenger passenger = (Passenger) DataHelper.Instance.getObject(passengerID, DataType.PASSENGER);
			Flight flight = (Flight) DataHelper.Instance.getObject(flightID, DataType.FLIGHT);

			String key = passengerID + ":" + flightID;
			while (true) {
				if (!DataHelper.Instance.containsKey(key, DataType.PASSENGER_BOOKING)) {
					PassengerBooking pBooking = new PassengerBooking();
					pBooking.setPassenger(passenger);
					pBooking.setFlight(flight);
					boolean bookingPossible = BookingHelper.bookingResolver(pBooking);
					if (bookingPossible) {
						DataHelper.Instance.addObject(pBooking, key, DataType.PASSENGER_BOOKING, OpType.INSERT);
						break;
					} else {
						JOptionPane.showMessageDialog(null,
								"Sorry. You have crossed the maximum number of bookings allowed", "ERROR BOOKING",
								JOptionPane.ERROR_MESSAGE);
						break;
					}

				}
				JOptionPane.showMessageDialog(null, "Passenger already booked for this flight", "ERROR",
						JOptionPane.ERROR_MESSAGE);
				break;
			}
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
	}

	// Display Passenger Booking
	public void displayPassengerBookingList() {
		LinkedHashMap<String, PassengerBooking> bookingMap = DataHelper.Instance.getList(DataType.PASSENGER_BOOKING);
		Iterator<PassengerBooking> bookingIterator = bookingMap.values().iterator();
		while (bookingIterator.hasNext()) {
			PassengerBooking passengerBooking = bookingIterator.next();
			passengerBooking.displayDetails();
		}
	}

	// Remove Passenger Booking
	public void removePassengerBooking() {
		String passengerID = JOptionPane.showInputDialog("Enter the Passenger ID");
		String flightID = JOptionPane.showInputDialog("Enter the Flight ID");

		String key = passengerID + ":" + flightID;
		while (true) {
			if (DataHelper.Instance.containsKey(key, DataType.PASSENGER_BOOKING)) {
				boolean isRemoved = DataHelper.Instance.removeObject(key, DataType.PASSENGER_BOOKING);
				if (isRemoved) {
					JOptionPane.showMessageDialog(null, "Record Deleted", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
					break;
				} else {
					JOptionPane.showMessageDialog(null, "Unable to delete the record", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					break;
				}
			}
			JOptionPane.showMessageDialog(null, "No booking record found in the system", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			break;
		}
	}

	// Store the data
	public void serializeData() {
		boolean result = FileHelper.serialize();
		if (result) {
			JOptionPane.showMessageDialog(null, "Successfully Saved", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Error storing records", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

}

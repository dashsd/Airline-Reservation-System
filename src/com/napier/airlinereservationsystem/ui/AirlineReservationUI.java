package com.napier.airlinereservation.ui;

import javax.swing.JOptionPane;

public class AirlineReservationUI {

	AirlineReservationService ars = new AirlineReservationService();

	public void displayMenu() {
		String output, choices;
		int choice = 0;
		do {
			output = getDisplayMessage();
			choices = JOptionPane.showInputDialog(output);
			try {
				choice = Integer.parseInt(choices);
				execute(choice);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Enter valid input", "WARNING", JOptionPane.WARNING_MESSAGE);
			}
		} while (choice != 12);
	}

	private void execute(int choice) {

		switch (choice) {
		case 1:
			String airlineCode = null;
			String airlineName = null;
			ars.addAirline(airlineCode, airlineName);
			break;
		case 2:
			ars.displayAirlineList();
			break;
		case 3:
			String flightID = null;
			String origin = null;
			String destination = null;
			String takeOffTime = null;
			String landingTime = null;
			ars.addFlight(flightID, origin, destination, takeOffTime, landingTime);
			break;
		case 4:
			ars.displayFlightList();
			break;
		case 5:
			String passengerID = null;
			String name = null;
			int age = 0;
			String address = null;
			ars.addPassenger(name, age, address, passengerID);
			break;
		case 6:
			ars.displayPassengerList();
			break;
		case 7:
			ars.removePassenger();
			break;
		case 8:
			passengerID = null;
			flightID = null;
			ars.addPassengerBooking(passengerID, flightID);
			break;
		case 9:
			ars.displayPassengerBookingList();
			break;
		case 10:
			ars.removePassengerBooking();
			break;
		case 11:
			ars.serializeData();
			break;
		case 12:
			System.exit(0);
			break;
		default:
			errorInputChoice();
		}

	}

	private void errorInputChoice() {
		String error;
		error = "Incorrect Choice selected.";
		JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private String getDisplayMessage() {
		String output;

		output = "Airline Reservation UI \n";
		output = output + "Select a menu option \n\n";
		output = output + "1 - Create and Add new Airline \n";
		output = output + "2 - Display all Airlines \n";
		output = output + "3 - Create and Add new Flights \n";
		output = output + "4 - Display all Flights \n";
		output = output + "5 - Add passengers \n";
		output = output + "6 - Display all Passengers \n";
		output = output + "7 - Remove Passenger \n";
		output = output + "8 - Book Passenger for a flight \n";
		output = output + "9 - Display Passenger Booking \n";
		output = output + "10 - Remove Passenger Booking \n";
		output = output + "11 - Save the Data entered \n";
		output = output + "12 - Exit";

		return output;
	}

}

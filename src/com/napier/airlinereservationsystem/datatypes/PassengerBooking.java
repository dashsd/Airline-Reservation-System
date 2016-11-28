package com.napier.airlinereservation.datatypes;

import com.napier.airlinereservation.helpers.Observable;

import java.io.Serializable;

import javax.swing.JOptionPane;

import com.napier.airlinereservation.helpers.DataHelper.OpType;

public class PassengerBooking extends Observable<PassengerBooking> implements Serializable {

	private static final long serialVersionUID = 5930832207488694177L;
	private Passenger passenger;
	private Flight flight;

	public PassengerBooking() {
	}

	public String toString() {
		String output;
		output = "PassengerID: " + passenger.getPassengerID() + "\nPassengerName: "
				+ passenger.getPersonName() + "\nFlight ID: " + flight.getFlightID();
		return output;
	}

	public void displayDetails() {
		String output;
		output = toString();
		JOptionPane.showMessageDialog(null, output, "Details of the booking", JOptionPane.INFORMATION_MESSAGE);
	}

	public Passenger getPassenger() {
		return this.passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Flight getFlight() {
		return this.flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public void update(OpType opType) {
		notifyObservers(this, opType);
	}

}

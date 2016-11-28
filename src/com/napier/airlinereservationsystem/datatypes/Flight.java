package com.napier.airlinereservation.datatypes;

import java.io.Serializable;

import javax.swing.JOptionPane;

import com.napier.airlinereservation.helpers.DataHelper;
import com.napier.airlinereservation.helpers.DataHelper.DataType;
import com.napier.airlinereservation.helpers.DataHelper.OpType;
import com.napier.airlinereservation.helpers.Observable;

public class Flight extends Observable<Flight> implements Serializable {

	private static final long serialVersionUID = 5893757679759603805L;
	private String flightID;
	private String flightOrigin;
	private String flightDestination;
	private String flightTakeOffTime;
	private String flightLandingTime;
	private String airlineCode;

	public Flight() {
	}

	public Flight(String flightID, String flightOrigin, String flightDestination, String flightTakeOffTime,
			String flightLandingTime) {
		setFlightID(flightID);
		setFlightOrigin(flightOrigin);
		setFlightDestination(flightDestination);
		setFlightTakeOffTime(flightTakeOffTime);
		setFlightLandingTime(flightLandingTime);
	}

	@Override
	public String toString() {
		String output;
		output = "Airline Name: " + getAirline().getAirlineName();
		output = output + "\nFlight ID: " + this.flightID + "\nOrigin: " + this.flightOrigin + "\nDestination: "
				+ this.flightDestination;
		output = output + "\nTake-Off Time: " + this.flightTakeOffTime + "\nArrival Time: " + this.flightLandingTime;
		return output;
	}

	public void displayDetails() {
		String output;
		output = toString();
		JOptionPane.showMessageDialog(null, output, "Details of the Flight", JOptionPane.INFORMATION_MESSAGE);
	}

	public String getFlightID() {
		return this.flightID;
	}

	public void setFlightID(String flightID) {
		this.flightID = flightID;
	}

	public String getFlightOrigin() {
		return this.flightOrigin;
	}

	public void setFlightOrigin(String flightOrigin) {
		this.flightOrigin = flightOrigin;
	}

	public String getFlightDestination() {
		return this.flightDestination;
	}

	public void setFlightDestination(String flightDestination) {
		this.flightDestination = flightDestination;
	}

	public String getFlightTakeOffTime() {
		return this.flightTakeOffTime;
	}

	public void setFlightTakeOffTime(String flightTakeOffTime) {
		this.flightTakeOffTime = flightTakeOffTime;
	}

	public String getFlightLandingTime() {
		return this.flightLandingTime;
	}

	public void setFlightLandingTime(String flightLandingTime) {
		this.flightLandingTime = flightLandingTime;
	}

	public Airline getAirline() {
		return (Airline) DataHelper.Instance.getObject(this.airlineCode, DataType.AIRLINE);
	}

	public void setAirline(Airline airline) {
		this.airlineCode = airline.getAirlineCode();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flightID == null) ? 0 : flightID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Flight))
			return false;
		Flight other = (Flight) obj;
		if (flightID == null) {
			if (other.flightID != null)
				return false;
		} else if (!flightID.equals(other.flightID))
			return false;
		return true;
	}

	public void update(OpType opType) {
		notifyObservers(this, opType);
	}

}

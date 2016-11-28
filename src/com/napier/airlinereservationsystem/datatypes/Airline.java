package com.napier.airlinereservation.datatypes;

import java.io.Serializable;

import javax.swing.JOptionPane;

import com.napier.airlinereservation.helpers.DataHelper.OpType;
import com.napier.airlinereservation.helpers.Observable;

public class Airline extends Observable<Airline> implements Serializable {

	private static final long serialVersionUID = 1260217919517302563L;
	private String airlineCode;
	private String airlineName;

	public Airline() {
	}

	public Airline(String airlineCode, String airlineName) {
		setAirlineCode(airlineCode);
		setAirlineName(airlineName);
	}

	@Override
	public String toString() {
		return airlineCode + ": " + airlineName;
	}
	
	public void displayDetails() {
		String output;
		output = toString();
		JOptionPane.showMessageDialog(null, output, "Details of the Airline", JOptionPane.INFORMATION_MESSAGE);
	}

	public String getAirlineName() {
		return this.airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getAirlineCode() {
		return this.airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public void update(OpType opType) {
		notifyObservers(this, opType);
	}

}

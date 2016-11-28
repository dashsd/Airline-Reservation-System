package com.napier.airlinereservation.datatypes;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.napier.airlinereservation.helpers.IObserver;
import com.napier.airlinereservation.helpers.IObservable;
import com.napier.airlinereservation.helpers.DataHelper.OpType;

public class Passenger extends Person implements IObservable<Passenger>, Serializable {

	private static final long serialVersionUID = -19354349093579764L;
	private String passengerID;
	private ArrayList<IObserver<Passenger>> observers = new ArrayList<>();

	public static enum PASSENGER_CLASS {
		STANDARD, BUSINESS;
	};

	private PASSENGER_CLASS passengerClass;
	
	public Passenger() {
	}

	public Passenger(String personName, int personAge, String personAddress, String passengerID) {
		super(personName, personAge, personAddress);
		setPassengerID(passengerID);
	}

	@Override
	public String toString() {
		String output;
		output = super.toString();
		output = output + "\nPassengerID: " + this.passengerID + "\nPassenger Class: " + this.passengerClass;
		return output;
	}

	public void displayDetails() {
		String output;
		output = toString();
		JOptionPane.showMessageDialog(null, output, "Details of the Passenger", JOptionPane.INFORMATION_MESSAGE);
	}

	public String getPassengerID() {
		return this.passengerID;
	}

	public void setPassengerID(String passengerID) {
		this.passengerID = passengerID;
	}

	public PASSENGER_CLASS getPassengerClass() {
		return this.passengerClass;
	}

	public void setPassengerClass(PASSENGER_CLASS passengerClass) {
		this.passengerClass = passengerClass;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((passengerID == null) ? 0 : passengerID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Passenger))
			return false;
		Passenger other = (Passenger) obj;
		if (passengerID == null) {
			if (other.passengerID != null)
				return false;
		} else if (!passengerID.equals(other.passengerID))
			return false;
		return true;
	}

	public void addObserver(IObserver<Passenger> observer) {
		synchronized (observers) {
			observers.add(observer);
		}
	}

	public void removeObserver(IObserver<Passenger> observer) {
		synchronized (observers) {
			observers.remove(observer);
		}
	}

	public void update(OpType opType) {
		notifyObservers(this, opType);
	}

	protected void notifyObservers(final Passenger tempPassenger, OpType opType) {
		synchronized (observers) {
			for (IObserver<Passenger> iObserver : observers) {
				iObserver.notify(tempPassenger, opType);
			}
		}
	}

}

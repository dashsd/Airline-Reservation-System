package com.napier.airlinereservation.datatypes;

import java.io.Serializable;

public abstract class Person implements Serializable {

	private static final long serialVersionUID = 2461332992458003662L;
	private String personName;
	private int personAge;
	private String personAddress;

	public Person() {
	}

	public Person(String personName, int personAge, String personAddress) {
		setPersonName(personName);
		setPersonAge(personAge);
		setPersonAddress(personAddress);
	}

	@Override
	public String toString() {
		String output;
		output = "Person Name: " + this.personName + "\nAge: " + this.personAge + "\nAddress: " + this.personAddress;
		return output;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public int getPersonAge() {
		return personAge;
	}

	public void setPersonAge(int personAge) {
		this.personAge = personAge;
	}

	public String getPersonAddress() {
		return personAddress;
	}

	public void setPersonAddress(String personAddress) {
		this.personAddress = personAddress;
	}

}

package com.napier.airlinereservation.jUnitTestsv4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.napier.airlinereservation.datatypes.Passenger;
import com.napier.airlinereservation.datatypes.Passenger.PASSENGER_CLASS;

public class PassengerTest {

	@Test
	public void testSetPassengerID() {
		Passenger p1 = new Passenger();
		String id = "P1";
		p1.setPassengerID(id);

		String actual = p1.getPassengerID();
		String expected = id;

		assertTrue(actual == expected);
	}

	@Test
	public void testSetPassengerClass() {
		Passenger p1 = new Passenger();
		PASSENGER_CLASS pClass = PASSENGER_CLASS.BUSINESS;
		p1.setPassengerClass(pClass);

		PASSENGER_CLASS actual = p1.getPassengerClass();
		PASSENGER_CLASS expected = pClass;

		assertTrue(actual == expected);
	}

	@Test
	public void testSetPassengerClass1() {
		Passenger p1 = new Passenger();
		PASSENGER_CLASS pClass = PASSENGER_CLASS.BUSINESS;
		p1.setPassengerClass(PASSENGER_CLASS.STANDARD);

		PASSENGER_CLASS actual = p1.getPassengerClass();
		PASSENGER_CLASS expected = pClass;

		assertFalse(actual == expected);
	}

}

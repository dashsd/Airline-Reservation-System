package com.napier.airlinereservation.jUnitTestsv3;

import com.napier.airlinereservation.datatypes.Airline;

import junit.framework.TestCase;

public class AirlineTest extends TestCase {

	public void testSetAirlineName() {
		Airline a1 = new Airline();
		String name = "Etihad";
		a1.setAirlineName(name);

		String actual = a1.getAirlineName();
		String expected = name;

		assertTrue(actual == expected);
	}

	public void testSetAirlineCode() {
		Airline a1 = new Airline();
		String code = "A1";
		a1.setAirlineCode(code);

		String actual = a1.getAirlineCode();
		String expected = code;

		assertTrue(actual == expected);
	}

}

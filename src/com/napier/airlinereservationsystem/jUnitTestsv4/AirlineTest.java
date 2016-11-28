package com.napier.airlinereservation.jUnitTestsv4;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.napier.airlinereservation.datatypes.Airline;

public class AirlineTest {

	@Test
	public void testSetAirlineName() {
		Airline a1 = new Airline();
		String name = "Etihad";
		a1.setAirlineName(name);

		String actual = a1.getAirlineName();
		String expected = name;

		assertTrue(actual == expected);
	}

	@Test
	public void testSetAirlineCode() {
		Airline a1 = new Airline();
		String code = "A1";
		a1.setAirlineCode(code);

		String actual = a1.getAirlineCode();
		String expected = code;

		assertTrue(actual == expected);
	}

}

package com.napier.airlinereservation.jUnitTestsv3;

import com.napier.airlinereservation.datatypes.Airline;
import com.napier.airlinereservation.datatypes.Flight;
import com.napier.airlinereservation.helpers.DataHelper;
import com.napier.airlinereservation.helpers.DataHelper.DataType;
import com.napier.airlinereservation.helpers.DataHelper.OpType;

import junit.framework.TestCase;

public class FlightTest extends TestCase {

	public void testSetFlightID() {
		Flight f1 = new Flight();
		String id = "F001";
		f1.setFlightID(id);

		String actual = f1.getFlightID();
		String expected = id;

		assertTrue(actual == expected);
	}

	public void testSetFlightOrigin() {
		Flight f1 = new Flight();
		String origin = "Edinburgh";
		f1.setFlightOrigin(origin);
		;

		String actual = f1.getFlightOrigin();
		String expected = origin;

		assertTrue(actual == expected);
	}

	public void testSetFlightDestination() {
		Flight f1 = new Flight();
		String destination = "London";
		f1.setFlightDestination(destination);

		String actual = f1.getFlightDestination();
		String expected = destination;

		assertTrue(actual == expected);
	}

	public void testSetFlightTakeOffTime() {
		Flight f1 = new Flight();
		String takeOffTime = "09:00";
		f1.setFlightTakeOffTime(takeOffTime);

		String actual = f1.getFlightTakeOffTime();
		String expected = takeOffTime;

		assertTrue(actual == expected);
	}

	public void testSetFlightLandingTime() {
		Flight f1 = new Flight();
		String landingTime = "14:30";
		f1.setFlightLandingTime(landingTime);

		String actual = f1.getFlightLandingTime();
		String expected = landingTime;

		assertTrue(actual == expected);
	}

	public void testSetAirline() {
		Flight f1 = new Flight();
		Airline a1 = new Airline();
		String code = "A001";
		String id = "F001";

		f1.setFlightID(id);
		DataHelper.Instance.addObject(f1, f1.getFlightID(), DataType.FLIGHT, OpType.INSERT);

		a1.setAirlineCode(code);
		DataHelper.Instance.addObject(a1, a1.getAirlineCode(), DataType.AIRLINE, OpType.INSERT);
		f1.setAirline(a1);

		Airline actual = (Airline) DataHelper.Instance.getObject(f1.getAirline().getAirlineCode(), DataType.AIRLINE);
		String expected = code;

		assertEquals(actual.getAirlineCode(), expected);
	}

}

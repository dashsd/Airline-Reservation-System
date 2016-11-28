package com.napier.airlinereservation.jUnitTestsv4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.napier.airlinereservation.datatypes.Flight;
import com.napier.airlinereservation.datatypes.Passenger;
import com.napier.airlinereservation.datatypes.PassengerBooking;
import com.napier.airlinereservation.helpers.DataHelper;
import com.napier.airlinereservation.helpers.DataHelper.DataType;
import com.napier.airlinereservation.helpers.DataHelper.OpType;

public class DataHelperTest {

	@Test
	public void testAddBooking() {
		PassengerBooking pb = new PassengerBooking();
		Flight f1 = new Flight();
		Passenger p1 = new Passenger();

		String flightID = "F001";
		String passengerID = "P001";
		f1.setFlightID(flightID);
		p1.setPassengerID(passengerID);

		pb.setFlight(f1);
		pb.setPassenger(p1);
		String key = passengerID + ":" + flightID;
		DataHelper.Instance.addObject(pb, key, DataType.PASSENGER_BOOKING, OpType.INSERT);

		PassengerBooking result = (PassengerBooking) DataHelper.Instance.getObject(key, DataType.PASSENGER_BOOKING);
		String actual = result.getPassenger().getPassengerID() + ":" + result.getFlight().getFlightID();
		String expected = key;

		assertEquals(actual, expected);
	}

	@Test
	public void testRemoveBooking() {
		PassengerBooking pb = new PassengerBooking();
		Flight f1 = new Flight();
		Passenger p1 = new Passenger();

		String flightID = "F001";
		String passengerID = "P001";
		f1.setFlightID(flightID);
		p1.setPassengerID(passengerID);

		pb.setFlight(f1);
		pb.setPassenger(p1);
		String key = passengerID + ":" + flightID;
		DataHelper.Instance.addObject(pb, key, DataType.PASSENGER_BOOKING, OpType.INSERT);

		DataHelper.Instance.removeObject(key, DataType.PASSENGER_BOOKING);
		PassengerBooking result = (PassengerBooking) DataHelper.Instance.getObject(key, DataType.PASSENGER_BOOKING);
		assertNull(result);
	}

}

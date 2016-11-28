package com.napier.airlinereservation.helpers;

import java.util.Iterator;
import java.util.LinkedHashMap;

import com.napier.airlinereservation.datatypes.Airline;
import com.napier.airlinereservation.datatypes.Passenger;
import com.napier.airlinereservation.datatypes.Passenger.PASSENGER_CLASS;
import com.napier.airlinereservation.datatypes.PassengerBooking;
import com.napier.airlinereservation.helpers.DataHelper.DataType;

public class BookingHelper {

	public static boolean bookingResolver(PassengerBooking passengerBooking) {

		Airline airline = passengerBooking.getFlight().getAirline();

		Passenger passenger = passengerBooking.getPassenger();

		PASSENGER_CLASS passengerClass = passenger.getPassengerClass();

		int maxAllowableBookings = 0;

		switch (passengerClass) {
		case STANDARD:
			maxAllowableBookings = 2;
			break;
		case BUSINESS:
			maxAllowableBookings = 4;
			break;
		default:
			return false;
		}

		LinkedHashMap<String, PassengerBooking> bookingMap = DataHelper.Instance.getList(DataType.PASSENGER_BOOKING);
		Iterator<PassengerBooking> iterator = bookingMap.values().iterator();
		int tillDateBookingCount = 0;

		while (iterator.hasNext()) {
			PassengerBooking booking = iterator.next();
			if (booking.getFlight().getAirline().getAirlineCode().equalsIgnoreCase(airline.getAirlineCode())
					&& booking.getPassenger().getPassengerID().equalsIgnoreCase(passenger.getPassengerID())) {
				tillDateBookingCount++;
			}
			if (tillDateBookingCount >= maxAllowableBookings) {
				return false;
			}
		}
		return true;
	}

}

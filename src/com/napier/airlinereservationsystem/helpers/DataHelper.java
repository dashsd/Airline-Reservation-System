package com.napier.airlinereservation.helpers;

import java.util.LinkedHashMap;

import com.napier.airlinereservation.datatypes.Airline;
import com.napier.airlinereservation.datatypes.Flight;
import com.napier.airlinereservation.datatypes.Passenger;
import com.napier.airlinereservation.datatypes.PassengerBooking;

public class DataHelper {

	private LinkedHashMap<String, Airline> airlineList;
	private LinkedHashMap<String, Flight> flightList;
	private LinkedHashMap<String, Passenger> passengerList;
	private LinkedHashMap<String, PassengerBooking> bookingList;

	@SuppressWarnings("rawtypes")
	private LinkedHashMap hashMap;

	public static final DataHelper Instance = new DataHelper();

	public enum DataType {
		AIRLINE, FLIGHT, PASSENGER, PASSENGER_BOOKING;
	}

	public enum OpType {
		INSERT, DELETE;
	}

	public DataHelper() {
		airlineList = new LinkedHashMap<>();
		flightList = new LinkedHashMap<>();
		passengerList = new LinkedHashMap<>();
		bookingList = new LinkedHashMap<>();
	}

	@SuppressWarnings("unchecked")
	public <T> LinkedHashMap<String, T> getList(DataType dataType) {
		switch (dataType) {
		case AIRLINE:
			return (LinkedHashMap<String, T>) airlineList;
		case FLIGHT:
			return (LinkedHashMap<String, T>) flightList;
		case PASSENGER:
			return (LinkedHashMap<String, T>) passengerList;
		case PASSENGER_BOOKING:
			return (LinkedHashMap<String, T>) bookingList;
		default:
			return null;
		}
	}

	public Object getObject(String key, DataType dataType) {
		boolean exists = containsKey(key, dataType);
		if (exists) {
			selectDataType(dataType);
			if (hashMap != null) {
				return hashMap.get(key);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public Object getObjectByIndex(int index, DataType dataType) {
		selectDataType(dataType);
		if (hashMap != null) {
			try {
				return hashMap.values().toArray()[index];
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	public boolean containsKey(String key, DataType dataType) {
		selectDataType(dataType);
		if (hashMap != null && hashMap.containsKey(key)) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean addObject(Object object, String key, DataType dataType, OpType opType) {
		selectDataType(dataType);
		hashMap.put(key, object);
		notify(object, dataType, OpType.INSERT);
		return true;
	}

	public boolean removeObject(String key, DataType dataType) {
		selectDataType(dataType);
		Object object = hashMap.get(key);
		hashMap.remove(key);
		notify(object, dataType, OpType.DELETE);
		return true;
	}

	public void notify(Object object, DataType dataType, OpType opType) {
		switch (dataType) {
		case AIRLINE:
			Airline airline = (Airline) object;
			airline.update(opType);
			break;
		case FLIGHT:
			Flight flight = (Flight) object;
			flight.update(opType);
			break;
		case PASSENGER:
			Passenger passenger = (Passenger) object;
			passenger.update(opType);
			break;
		case PASSENGER_BOOKING:
			PassengerBooking booking = (PassengerBooking) object;
			booking.update(opType);
			break;
		default:
			break;
		}
	}

	public void selectDataType(DataType dataType) {
		hashMap = null;
		switch (dataType) {
		case AIRLINE:
			hashMap = airlineList;
			break;
		case FLIGHT:
			hashMap = flightList;
			break;
		case PASSENGER:
			hashMap = passengerList;
			break;
		case PASSENGER_BOOKING:
			hashMap = bookingList;
			break;
		default:
			hashMap = null;
		}
	}

}

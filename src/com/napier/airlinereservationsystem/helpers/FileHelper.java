package com.napier.airlinereservation.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import java.util.Set;

import com.napier.airlinereservation.datatypes.Airline;
import com.napier.airlinereservation.datatypes.Flight;
import com.napier.airlinereservation.datatypes.Passenger;
import com.napier.airlinereservation.datatypes.PassengerBooking;
import com.napier.airlinereservation.helpers.DataHelper.DataType;
import com.napier.airlinereservation.helpers.DataHelper.OpType;

public class FileHelper {

	public static final String BASE_PATH = "C:\\Siddhartha-Dash\\Airline Reservation System";

	public static boolean serialize() {

		try {

			File storageDir = new File(BASE_PATH);
			if (!storageDir.exists()) {
				storageDir.mkdirs();
			}

			// Airline Serialization
			File file = new File(storageDir + "//airline.txt");
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(DataHelper.Instance.getList(DataType.AIRLINE));
			oos.close();
			fos.close();

			// Flight Serialization
			file = new File(storageDir + "//flight.txt");
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(DataHelper.Instance.getList(DataType.FLIGHT));
			oos.close();
			fos.close();

			// Passenger Serialization
			file = new File(storageDir + "//passenger.txt");
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(DataHelper.Instance.getList(DataType.PASSENGER));
			oos.close();
			fos.close();

			// Booking Serialization
			file = new File(storageDir + "//booking.txt");
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(DataHelper.Instance.getList(DataType.PASSENGER_BOOKING));
			oos.close();
			fos.close();

			return true;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean deserialize() {
		deserializeAirline();
		deserializeFlight();
		deserializePassenger();
		deserializeBooking();
		return true;
	}

	// Deserialize Airline data
	@SuppressWarnings("unchecked")
	public static boolean deserializeAirline() {
		LinkedHashMap<String, Airline> airlineHashMap = new LinkedHashMap<>();
		try {
			FileInputStream fis = new FileInputStream(BASE_PATH + "//airline.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			airlineHashMap = (LinkedHashMap<String, Airline>) ois.readObject();
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Airline File not found", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Airline Class not found", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "I/O operation error", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		Set<Entry<String, Airline>> airlineSet = airlineHashMap.entrySet();
		Iterator<Entry<String, Airline>> iterator = airlineSet.iterator();
		@SuppressWarnings("unused")
		int count = 0;
		while (iterator.hasNext()) {
			Map.Entry<String, Airline> mapEntry = (Map.Entry<String, Airline>) iterator.next();
			DataHelper.Instance.addObject(mapEntry.getValue(), mapEntry.getKey(), DataType.AIRLINE, OpType.INSERT);
			count++;
		}
		return true;
	}

	// Deserialize Flight Data
	@SuppressWarnings("unchecked")
	public static boolean deserializeFlight() {
		LinkedHashMap<String, Flight> flightHashMap = new LinkedHashMap<>();
		try {
			FileInputStream fis = new FileInputStream(BASE_PATH + "//flight.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			flightHashMap = (LinkedHashMap<String, Flight>) ois.readObject();
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Flight File not found", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Flight Class not found", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "I/O operation error", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		Set<Entry<String, Flight>> flightSet = flightHashMap.entrySet();
		Iterator<Entry<String, Flight>> iterator = flightSet.iterator();
		@SuppressWarnings("unused")
		int count = 0;
		while (iterator.hasNext()) {
			Map.Entry<String, Flight> mapEntry = (Map.Entry<String, Flight>) iterator.next();
			DataHelper.Instance.addObject(mapEntry.getValue(), mapEntry.getKey(), DataType.FLIGHT, OpType.INSERT);
			count++;
		}
		return true;
	}

	// Deserialize Passenger Data
	@SuppressWarnings("unchecked")
	public static boolean deserializePassenger() {
		LinkedHashMap<String, Passenger> passengerHashMap = new LinkedHashMap<>();
		try {
			FileInputStream fis = new FileInputStream(BASE_PATH + "//passenger.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			passengerHashMap = (LinkedHashMap<String, Passenger>) ois.readObject();
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Passenger File not found", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Passenger Class not found", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "I/O operation error", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		Set<Entry<String, Passenger>> passengerSet = passengerHashMap.entrySet();
		Iterator<Entry<String, Passenger>> iterator = passengerSet.iterator();
		@SuppressWarnings("unused")
		int count = 0;
		while (iterator.hasNext()) {
			Map.Entry<String, Passenger> mapEntry = (Map.Entry<String, Passenger>) iterator.next();
			DataHelper.Instance.addObject(mapEntry.getValue(), (String) mapEntry.getKey(), DataType.PASSENGER,
					OpType.INSERT);
			count++;
		}
		return true;
	}

	// Deserialize Booking Data
	@SuppressWarnings("unchecked")
	public static boolean deserializeBooking() {
		LinkedHashMap<String, PassengerBooking> bookingHashMap = new LinkedHashMap<>();
		try {
			FileInputStream fis = new FileInputStream(BASE_PATH + "//booking.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			bookingHashMap = (LinkedHashMap<String, PassengerBooking>) ois.readObject();
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Booking File not found", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Booking Class not found", "ERROR", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "I/O operation error", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		Set<Entry<String, PassengerBooking>> bookingSet = bookingHashMap.entrySet();
		Iterator<Entry<String, PassengerBooking>> iterator = bookingSet.iterator();
		@SuppressWarnings("unused")
		int count = 0;
		while (iterator.hasNext()) {
			Map.Entry<String, PassengerBooking> mapEntry = (Map.Entry<String, PassengerBooking>) iterator.next();
			DataHelper.Instance.addObject(mapEntry.getValue(), mapEntry.getKey(), DataType.PASSENGER_BOOKING,
					OpType.INSERT);
			count++;
		}
		return true;
	}

}

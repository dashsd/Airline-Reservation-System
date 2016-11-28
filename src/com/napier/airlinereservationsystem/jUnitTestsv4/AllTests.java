package com.napier.airlinereservation.jUnitTestsv4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AirlineTest.class, DataHelperTest.class, FlightTest.class, PassengerBookingTest.class,
		PassengerTest.class })
public class AllTests {

}

package com.napier.airlinereservation.jUnitTestsv3;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests extends TestCase {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(AirlineTest.class);
		suite.addTestSuite(DataHelperTest.class);
		suite.addTestSuite(FlightTest.class);
		suite.addTestSuite(PassengerBookingTest.class);
		suite.addTestSuite(PassengerTest.class);
		//$JUnit-END$
		return suite;
	}

}

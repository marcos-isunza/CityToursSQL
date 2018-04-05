package com.infy.test;

import java.util.Calendar;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.infy.bean.Booking;
import com.infy.bean.Tour;
import com.infy.business.service.TourService;
import com.infy.resources.Factory;


public class TourServiceTest {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void bookTourInvalidTravelDateTest() throws Exception {
		//write your logic class here
			//Do not change the signature of the class and method
		expectedException.expect(Exception.class);
		expectedException
				.expectMessage("Validator.INVALID_TRAVELDATE");
		Tour tour = new Tour();
		tour.setCity("MySore");
		tour.setTourPackage("P");
		Booking booking = new Booking();
		booking.setCustomerName("Smith");	
		Calendar travelDate = Calendar.getInstance();
		travelDate.add(Calendar.DATE, -2);
		booking.setTravelDate(travelDate);	
		
		booking.setTour(tour);
		

		TourService service=Factory.createTourService();
		service.bookTour(booking);
	}
	@Test
	public void bookTourTourNotAvailableTest() throws Exception {
		//write your logic class here
		//Do not change the signature of the class and method
		expectedException.expect(Exception.class);
		expectedException
				.expectMessage("Service.NO_TOUR_AVAILABLE");
		Tour tour = new Tour();
		tour.setCity("Odisha");
		tour.setTourPackage("P");
		Booking booking = new Booking();
		booking.setCustomerName("Smith");	
		Calendar travelDate = Calendar.getInstance();
		travelDate.add(Calendar.DATE, 2);
		booking.setTravelDate(travelDate);
		
		booking.setTour(tour);
	}
	@Test
	public void bookTourPackageNotAvailableTest() throws Exception {
		//write your logic class here
		//Do not change the signature of the class and method
		expectedException.expect(Exception.class);
		expectedException
				.expectMessage("Service.TOUR_PACKAGE_NOT_AVAILABLE");
		Tour tour = new Tour();
		tour.setCity("MySore");
		tour.setTourPackage("S");
		Booking booking = new Booking();
		booking.setCustomerName("Smith");	
		Calendar travelDate = Calendar.getInstance();
		travelDate.add(Calendar.DATE, 2);
		booking.setTravelDate(travelDate);
		
		booking.setTour(tour);
	}
	}
	

	



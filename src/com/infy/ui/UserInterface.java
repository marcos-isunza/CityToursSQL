package com.infy.ui;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.infy.bean.Booking;
import com.infy.bean.Tour;
import com.infy.business.service.TourService;
import com.infy.resources.AppConfig;
import com.infy.resources.Factory;
import com.infy.resources.HibernateUtility;

public class UserInterface {

	public static void main(String[] args) {
		HibernateUtility.createSessionFactory();
		try {

			//bookTour();
			updateFare();
			//getBookingReport();
		} finally {
			HibernateUtility.closeSessionFactory();
		}

	}


	public static void bookTour() {
		try {

			Tour tour = new Tour();
			tour.setCity("Goa");
			tour.setTourPackage("S");
			Booking booking = new Booking();
			booking.setBookingId(generateBookingId());
			booking.setCustomerName("Jim");	
			Calendar travelDate = Calendar.getInstance();
			travelDate.add(Calendar.DATE, 2);
			booking.setTravelDate(travelDate);
			
			booking.setTour(tour);
			

			TourService service=Factory.createTourService();
			Integer bookingId = service.bookTour(booking);
			DOMConfigurator.configure("src/com/infy/resources/log4j.xml");
			Logger logger = Logger.getLogger(AppConfig.class);
			logger.info("UserInterface.SUCCESS_BOOKING"+bookingId);
			System.out.println("\n\n"+AppConfig.PROPERTIES.getProperty("UserInterface.SUCCESS_BOOKING")+bookingId);

		} catch (Exception e) {
			String message = AppConfig.PROPERTIES.getProperty(e.getMessage());
			
			if (message == null) {
				message = AppConfig.PROPERTIES.getProperty("General.EXCEPTION");
			}
			System.out.println("\n\nERROR:" + message);
		}

	}
	public static void updateFare() {
		Double incrementAmount=250.0;
		String city = "Goa";
		try {
			TourService service=Factory.createTourService();
			Integer noOfRecords=service.updateFare(incrementAmount, city);
			DOMConfigurator.configure("src/com/infy/resources/log4j.xml");
			Logger logger = Logger.getLogger(AppConfig.class);
			logger.info("UserInterface.SUCCESS_UPDATERECORDS"+" "+noOfRecords);
			System.out.println("\n\n"+AppConfig.PROPERTIES.getProperty("UserInterface.SUCCESS_UPDATERECORDS")+" "+noOfRecords);

		} catch (Exception e) {
			String message = AppConfig.PROPERTIES.getProperty(e.getMessage());
			if (message == null) {
				message = AppConfig.PROPERTIES.getProperty("General.EXCEPTION");
			}
			System.out.println("\n\nERROR:" + message);
		}
	}

	public static void getBookingReport() {
		
		Calendar travelDate = Calendar.getInstance();
		//travelDate.add(Calendar.DATE, 1);
		
		String city = "Chennai";

		try {
			TourService service=Factory.createTourService();
			List<Booking> bookings=service.getBookingReport(city, travelDate);

			if(bookings!=null){
				System.out.println("\n\nBooking Report  is : ");
				System.out.println("\n Booking Id  "+"\tCustomerName "+"\t Tour Id   "+"\t    City ");
				System.out.println(" ***********  "+"\t**************"+"\t**************   "+" *************");
				for(Booking b:bookings){
					DOMConfigurator.configure("src/com/infy/resources/log4j.xml");
					Logger logger = Logger.getLogger(AppConfig.class);
					logger.info(b.getBookingId()+" "+b.getCustomerName()+" "+b.getTour().getTourId()+" "+b.getTour().getCity());
					System.out.println("   "+b.getBookingId()+"\t\t     "+b.getCustomerName()+"\t"+
					b.getTour().getTourId()+"\t     "+"\t   "+b.getTour().getCity());
				}

			}


		} catch (Exception e) {
			String message = AppConfig.PROPERTIES.getProperty(e.getMessage());
			if (message == null) {
				message = AppConfig.PROPERTIES.getProperty("General.EXCEPTION");
			}
			System.out.println("\n\nERROR:" + message);
		}
	}

	public static Integer generateBookingId() {
		Random rand = new Random();
		int value = rand.nextInt(1000);
		return value;
	}


	

}

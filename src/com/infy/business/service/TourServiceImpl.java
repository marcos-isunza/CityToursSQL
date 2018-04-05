package com.infy.business.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.infy.bean.Booking;
import com.infy.bean.Tour;
import com.infy.business.validator.Validator;
import com.infy.dao.TourDAO;
import com.infy.resources.Factory;
public class TourServiceImpl implements TourService {

	
	
	
	//Don't tamper with the signature
	public Integer bookTour(Booking booking) throws Exception {
		//write your code here
		Validator validator = new Validator();
		List<Tour> toursByCity = new ArrayList<Tour>();
		Integer bookingId = null;
		try
		{
			validator.validateTravelDate(booking.getTravelDate());
			TourDAO dao = Factory.createTourDAO();
			toursByCity = dao.getToursByCity(booking.getTour().getCity());
			if(toursByCity.isEmpty())
			{
				throw new Exception("Service.NO_TOUR_AVAILABLE");
			}
			for(Tour t : toursByCity)
			{
				if(t.getTourPackage().equalsIgnoreCase(booking.getTour().getTourPackage()) && t.getAvailability().equalsIgnoreCase("Y"))
				{
					Booking b = new Booking();
					b.setBookingId(booking.getBookingId());
					b.setCustomerName(booking.getCustomerName());
					b.setBillAmount(t.getTourCost());
					b.setTravelDate(booking.getTravelDate());
					Tour tour = new Tour();
					tour.setCity(booking.getTour().getCity());
					tour.setAvailability(t.getAvailability());
					tour.setTourCost(t.getTourCost());
					tour.setTourId(t.getTourId());
					tour.setTourPackage(booking.getTour().getTourPackage());
					b.setTour(tour);
					bookingId = dao.bookTour(b);
					break;
				}
				else
				{
					throw new Exception("Service.TOUR_PACKAGE_NOT_AVAILABLE");
				}
			}
			return bookingId;
			
		}
		catch(Exception exception)
		{
			DOMConfigurator.configure("src/com/infy/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);
			throw exception;
		}
	}
	//Don't tamper with the signature
	public Integer updateFare(Double incrementAmount, String city) throws Exception{
		//write your code here
		TourDAO dao = Factory.createTourDAO();
		Integer recordsUpdated = null;
		try{
			recordsUpdated = dao.updateFare(incrementAmount, city);
			if(recordsUpdated == 0)
			{
				throw new Exception("Service.NO_RECORDS_FOUND");
			}
			return recordsUpdated;
			
		}catch(Exception exception)
		{
			DOMConfigurator.configure("src/com/infy/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);
			throw exception;
		}
	}
	//Don't tamper with the signature
	public List<Booking> getBookingReport(String city, Calendar travelDate) throws Exception {
		//write your code here
		TourDAO dao = Factory.createTourDAO();
		List<Booking> bookingList = null;
		try{
			bookingList = dao.getBookingReport(city, travelDate);
			if(bookingList.isEmpty())
			{
				throw new Exception("Service.NO_BOOKING_FOUND");
			}
			return bookingList;
			
		}catch(Exception exception)
		{
			DOMConfigurator.configure("src/com/infy/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);
			throw exception;
		}
	}
	
}

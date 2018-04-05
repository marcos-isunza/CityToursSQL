package com.infy.dao;

import java.util.Calendar;
import java.util.List;

import com.infy.bean.Booking;
import com.infy.bean.Tour;

public interface TourDAO {

	
	public List<Tour> getToursByCity(String city) throws Exception;
	public Integer bookTour(Booking	booking) throws Exception;	
	public Integer updateFare(Double incrementAmount, String city) throws Exception;
	public List<Booking>  getBookingReport(String city, Calendar travelDate) throws Exception;
	
}


package com.infy.business.service;

import java.util.Calendar;
import java.util.List;

import com.infy.bean.Booking;

public interface TourService {
	
	public Integer bookTour(Booking booking) throws Exception;	
	public Integer updateFare(Double incrementAmount, String city) throws Exception;
	public List<Booking> getBookingReport(String city, Calendar travelDate) throws Exception;
}

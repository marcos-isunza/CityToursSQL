package com.infy.bean;

import java.util.Calendar;


public class Booking {
	private Integer bookingId;
	private String customerName;	
	private Calendar travelDate;
	private Double billAmount;	
	private Tour tour;
	
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public Calendar getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(Calendar travelDate) {
		this.travelDate = travelDate;
	}
	public Tour getTour() {
		return tour;
	}
	public void setTour(Tour tour) {
		this.tour = tour;
	}
	public Double getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}
	

	
	
}

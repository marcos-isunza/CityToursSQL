package com.infy.entity;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Booking")
//@GenericGenerator(name="generatorName", strategy = "GenerationType.AUTO")
public class BookingEntity {
	//write your entity class here
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingId;
	private String customerName;
	@Temporal(TemporalType.DATE)
	private Calendar travelDate;
	private Double billAmount;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="tourId")
	private TourEntity tour;
	
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
	public Double getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}
	public TourEntity getTour() {
		return tour;
	}
	public void setTour(TourEntity tour) {
		this.tour = tour;
	}

}

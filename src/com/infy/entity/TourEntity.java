package com.infy.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tour")
public class TourEntity {
	//write your entity class here
	@Id
	private String tourId;
	private String city;
	private Double tourCost;
	private String tourPackage;
	private String availability;
	
	public String getTourId() {
		return tourId;
	}
	public void setTourId(String tourId) {
		this.tourId = tourId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Double getTourCost() {
		return tourCost;
	}
	public void setTourCost(Double tourCost) {
		this.tourCost = tourCost;
	}
	public String getTourPackage() {
		return tourPackage;
	}
	public void setTourPackage(String tourPackage) {
		this.tourPackage = tourPackage;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
}

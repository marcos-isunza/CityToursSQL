package com.infy.bean;




public class Tour {

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
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public String getTourPackage() {
		return tourPackage;
	}
	public void setTourPackage(String tourPackage) {
		this.tourPackage = tourPackage;
	}
	
	

}

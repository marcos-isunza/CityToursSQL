package com.infy.resources;

import com.infy.business.service.TourServiceImpl;
import com.infy.dao.TourDAOImpl;


public class Factory {
	public static TourServiceImpl createTourService() {
		return new TourServiceImpl();
	}
	
	public static TourDAOImpl createTourDAO() {
		return new TourDAOImpl();
	}
}

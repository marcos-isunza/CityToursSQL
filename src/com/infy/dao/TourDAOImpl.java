package com.infy.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.bean.Booking;
import com.infy.bean.Tour;
import com.infy.entity.BookingEntity;
import com.infy.entity.TourEntity;
import com.infy.resources.HibernateUtility;

public class TourDAOImpl implements TourDAO {

	//Don't tamper with the signature
	@SuppressWarnings("unchecked")
	public List<Tour> getToursByCity(String city) throws Exception {
		//write your code here
		SessionFactory sessionFactory=null;
		Session session=null;
		String hql = "from TourEntity te where lower(te.city)=lower(:city)";
		List<TourEntity> getTours = new ArrayList<TourEntity>();
		List<Tour> tours = new ArrayList<Tour>();
		Tour tour = null;
		try {	
			sessionFactory=HibernateUtility.createSessionFactory();
			session=sessionFactory.openSession();
			
			Query query = session.createQuery(hql);
			query.setParameter("city", city);
			getTours = query.list();
			if(getTours == null)
			{
				return null;
			}

			for(TourEntity t : getTours)
			{
				tour = new Tour();
				tour.setTourId(t.getTourId());
				tour.setCity(t.getCity());
				tour.setTourCost(t.getTourCost());
				tour.setAvailability(t.getAvailability());
				tour.setTourPackage(t.getTourPackage());
				tours.add(tour);
			}
			return tours;

			
		} catch (HibernateException exception) {
			DOMConfigurator.configure("src/com/infy/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);
			throw new Exception("DAO.TECHNICAL_ERROR");
		} catch (Exception exception) {
			DOMConfigurator.configure("src/com/infy/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);
			throw exception;
		} finally {
			session.close();
			
		}
	}


	//Don't tamper with the signature
	public Integer bookTour(Booking booking) throws Exception {
		//write your code here
		SessionFactory sessionFactory= null;
		Session session=null;
		Integer bId = null;
		try
		{
			sessionFactory=HibernateUtility.createSessionFactory();
			session=sessionFactory.openSession();
			
			
			
			BookingEntity be = new BookingEntity();
			be.setBookingId(booking.getBookingId());
			be.setCustomerName(booking.getCustomerName());
			be.setTravelDate(booking.getTravelDate());
			be.setBillAmount(booking.getBillAmount());
			TourEntity te = new TourEntity();
			te.setTourId(booking.getTour().getTourId());
			te.setAvailability(booking.getTour().getAvailability());
			te.setCity(booking.getTour().getCity());
			te.setTourCost(booking.getTour().getTourCost());
			te.setTourPackage(booking.getTour().getTourPackage());
			be.setTour(te);
			session.beginTransaction();
			bId = (Integer) session.save(be);
			session.getTransaction().commit();
			return bId;
		}
		catch (HibernateException exception) {
			DOMConfigurator.configure("src/com/infy/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception); 
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		catch (Exception exception) {
			DOMConfigurator.configure("src/com/infy/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);
			throw exception;
		}
		finally {
			if(session.isOpen()|| session!=null){
				session.close();
			}
		}
	}

	//Don't tamper with the signature
	@SuppressWarnings("unchecked")
	public Integer updateFare(Double incrementAmount, String city) throws Exception{
		//write your code here
		SessionFactory sessionFactory=null;
		Session session=null;
		String hql = "select tourId from TourEntity te where lower(te.city)=lower(:city) and tourCost>4500";
		String costhql = "Update TourEntity te set te.tourCost=:cost where te.tourId=:id";
		List<String> citiesId = new ArrayList<String>();
		Integer noOfRows = null;
		try {
			sessionFactory=HibernateUtility.createSessionFactory();
			session=sessionFactory.openSession();
			
			Query query = session.createQuery(hql);
			query.setParameter("city", city);
			citiesId = query.list();
			
			for(String cId : citiesId)
			{
				session.beginTransaction();
				TourEntity te = (TourEntity) session.get(TourEntity.class, cId);
				Double cost = te.getTourCost() + incrementAmount;
				Query updateQuery = session.createQuery(costhql);
				updateQuery.setParameter("id", cId);
				updateQuery.setParameter("cost", cost);
				noOfRows = updateQuery.executeUpdate();
				session.getTransaction().commit();
			}

			return noOfRows;
			
		
		}
		catch (HibernateException exception) {
			
			DOMConfigurator.configure("src/com/infy/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception); 
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		catch (Exception exception) {
			DOMConfigurator.configure("src/com/infy/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);
			throw exception;
		}
		finally {
			if(session.isOpen()|| session!=null){
				session.close();
			}
		}

	}
	//Don't tamper with the signature
	@SuppressWarnings("unchecked")
	public List<Booking> getBookingReport(String city, Calendar travelDate)
			throws Exception {
		//write your code here
		SessionFactory sessionFactory=null;
		Session session=null;
		String hql = "from BookingEntity be where lower(be.tour.city)=lower(:city)";
		List<BookingEntity> getBooking = new ArrayList<BookingEntity>();
		List<Booking> bookingList = new ArrayList<Booking>();
		try
		{
			sessionFactory=HibernateUtility.createSessionFactory();
			session=sessionFactory.openSession();
			
			Query query = session.createQuery(hql);
			query.setParameter("city", city);
			getBooking = query.list();
			
			for(BookingEntity b : getBooking)
			{
				if(b.getTravelDate().after(travelDate))
				{
					Booking bk = new Booking();
					bk.setBookingId(b.getBookingId());
					bk.setCustomerName(b.getCustomerName());
					bk.setTravelDate(b.getTravelDate());
					bk.setBillAmount(b.getBillAmount());
					Tour t = new Tour();
					t.setAvailability(b.getTour().getAvailability());
					t.setCity(b.getTour().getCity());
					t.setTourCost(b.getTour().getTourCost());
					t.setTourId(b.getTour().getTourId());
					t.setTourPackage(b.getTour().getTourPackage());
					bk.setTour(t);
					bookingList.add(bk);
				}
			}
			
			if(bookingList.isEmpty())
			{
				return null;
			}
			
			return bookingList;
			
		}
		catch (HibernateException exception) {
			DOMConfigurator.configure("src/com/infy/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception); 
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		catch (Exception exception) {
			DOMConfigurator.configure("src/com/infy/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);
			throw exception;
		}
		finally {
			if(session.isOpen()|| session!=null){
				session.close();
			}
		}
	}


}

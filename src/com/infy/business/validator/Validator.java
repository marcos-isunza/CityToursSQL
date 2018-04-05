package com.infy.business.validator;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Validator {

	//Don't tamper with the signature
	public void validateTravelDate( Calendar travelDate) throws Exception {
		//write your validator logic here
		Calendar today = Calendar.getInstance();
		try{
			if(travelDate.before(today))
			{
				throw new Exception("Validator.INVALID_TRAVELDATE");
			}
		}catch(Exception exception)
		{
			DOMConfigurator.configure("src/com/infy/resources/log4j.xml");
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(exception.getMessage(), exception);
			throw exception;
		}

	}
}

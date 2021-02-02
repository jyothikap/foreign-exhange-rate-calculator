package com.fundsaccess.services.blueprint.exception;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.apache.commons.lang3.StringUtils;

public class ExceptionBuilder {
	
	public static void throwExceptionIfDataNullOrEmpty(String parameterName, String value)
	{
		if(StringUtils.isEmpty(value))
    	{
    		 throw new DataNotProvidedException("Parameter name: "+ parameterName);
    	}
	}
	
	public static void throwExceptionIfInvalidDate(String dateFromClient)
	{
		try {
    		LocalDate.parse(dateFromClient);
    	}
    	catch(DateTimeParseException ex)
    	{
    		throw new InvalidDataException("Expected date format: \"yyyy-MM-dd\". "+ ex.getMessage());
    	}
	}

}

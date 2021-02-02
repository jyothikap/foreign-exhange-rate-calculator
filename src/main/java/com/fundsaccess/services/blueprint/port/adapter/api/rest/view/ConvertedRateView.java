package com.fundsaccess.services.blueprint.port.adapter.api.rest.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fundsaccess.services.blueprint.domain.model.CompactData;
import com.fundsaccess.services.blueprint.domain.model.Obs;

public class ConvertedRateView {

	private double  convertedAmount;
	private String currency;
	private String date;
	private String status;
	
	public ConvertedRateView(Double amount, String currency, String date)  {		 

		this.convertedAmount = amount;
		this.currency = currency;
		this.date = date;
		if(amount == 0.0)
		{
			this.status = "There was not conversion rate available for the provided date";
		}
		else
		{
			this.status = "Succesfully calculated the conversion amount";
		}
	}

}

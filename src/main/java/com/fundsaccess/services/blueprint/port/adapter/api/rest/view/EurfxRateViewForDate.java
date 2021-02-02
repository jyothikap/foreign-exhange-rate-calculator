package com.fundsaccess.services.blueprint.port.adapter.api.rest.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.fundsaccess.services.blueprint.domain.model.CompactData;
import com.fundsaccess.services.blueprint.domain.model.CurrencyDetails;
import com.fundsaccess.services.blueprint.domain.model.Obs;

public class EurfxRateViewForDate {
	
	private String currency;
	private Set<Obs> exchangeDetails;
	
	public EurfxRateViewForDate(CurrencyDetails currencyDetails, String dateStringFromClient)  {		 
		 this.currency = currencyDetails.currency;
		 this.exchangeDetails = currencyDetails.obsCollection;
		
	}

}

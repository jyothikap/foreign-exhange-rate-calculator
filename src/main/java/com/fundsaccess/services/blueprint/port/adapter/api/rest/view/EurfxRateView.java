package com.fundsaccess.services.blueprint.port.adapter.api.rest.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fundsaccess.services.blueprint.domain.model.CompactData;
import com.fundsaccess.services.blueprint.domain.model.JsonDateSerializer;
import com.fundsaccess.services.blueprint.domain.model.Obs;

public class EurfxRateView {
	
	private String currency;
	private List<Obs> exchangeDetails;
	
	public List<Obs> getexchangeDetails() {
		return exchangeDetails;
	}

	public void setRate(List<Obs> rate) {
		this.exchangeDetails = rate;
	}
	
	public EurfxRateView(CompactData blueprint)  {
        this.currency = blueprint.dataSet.series.currency;
        this.exchangeDetails = blueprint.dataSet.series.Obs;
    }

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
	

}

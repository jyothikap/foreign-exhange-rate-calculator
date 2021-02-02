package com.fundsaccess.services.blueprint.domain.model;

import java.util.List;
import java.util.Set;

public class CurrencyDetails {

	public String currency;
	public Set<Obs> obsCollection;
	
	
	public CurrencyDetails(String currency, Set<Obs> obsCollection) {
		super();
		this.currency = currency;
		this.obsCollection = obsCollection;
	}
}

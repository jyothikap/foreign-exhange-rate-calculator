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
	private String status;
	
	public ConvertedRateView(CompactData currencyCollection, String dateStringFromClient, String amountForConvertion, String currencyType)  {		 

		this.currency = currencyCollection.dataSet.series.UNIT;
		if(currencyCollection.dataSet.series.UNIT.equalsIgnoreCase(currencyType)) {
		 DateTimeFormatter f = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );
		 for(Obs temp: currencyCollection.dataSet.series.Obs)
		 {
				LocalDate dateFromClientParsed = LocalDate.parse(dateStringFromClient, f );
	        	LocalDate dateFromDB = LocalDate.parse(temp.TIME_PERIOD, f );
				if(dateFromClientParsed.compareTo(dateFromDB)==0)
				{
					if(StringUtils.isEmpty(temp.BBK_OBS_STATUS))
					{
						this.convertedAmount =Double.parseDouble(amountForConvertion)/Double.parseDouble(temp.OBS_VALUE);
						this.status = "successfully converted the amount";
					}
					else
					{
						this.convertedAmount = 0;
						this.status = "No conversion rate available for the specified date";
					}
				}
		 }
		}
	}

}

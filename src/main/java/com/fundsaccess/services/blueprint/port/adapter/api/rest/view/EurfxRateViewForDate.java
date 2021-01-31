package com.fundsaccess.services.blueprint.port.adapter.api.rest.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fundsaccess.services.blueprint.domain.model.CompactData;
import com.fundsaccess.services.blueprint.domain.model.Obs;
import com.fundsaccess.services.blueprint.domain.model.ObsForResponse;

public class EurfxRateViewForDate {
	
	private List<Obs> obs;
	private String currency;
	
	public EurfxRateViewForDate(CompactData currencyCollection, String dateStringFromClient)  {		 
		 this.currency = currencyCollection.dataSet.series.UNIT;
		 DateTimeFormatter f = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );
		 for(Obs temp: currencyCollection.dataSet.series.Obs)
		 {
			 this.obs = new ArrayList<Obs>();
				LocalDate dateFromClientParsed = LocalDate.parse(dateStringFromClient, f );
	        	LocalDate dateFromDB = LocalDate.parse(temp.TIME_PERIOD, f );
				if(dateFromClientParsed.compareTo(dateFromDB)==0)
				{
					if(!StringUtils.isEmpty(temp.BBK_OBS_STATUS))
					{
						continue;
					}
					// System.out.println("Found a match!");
					this.obs.add(temp);
					return;
				}
		 }
		
	}

}

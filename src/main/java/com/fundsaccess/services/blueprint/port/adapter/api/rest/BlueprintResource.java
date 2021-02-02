package com.fundsaccess.services.blueprint.port.adapter.api.rest;

import com.fundsaccess.services.blueprint.application.EuroFxRateApplicationService;
import com.fundsaccess.services.blueprint.domain.model.CompactData;
import com.fundsaccess.services.blueprint.domain.model.CurrencyDetails;
import com.fundsaccess.services.blueprint.domain.model.Obs;
import com.fundsaccess.services.blueprint.exception.DataNotFoundException;
import com.fundsaccess.services.blueprint.exception.DataNotProvidedException;
import com.fundsaccess.services.blueprint.exception.ExceptionBuilder;
import com.fundsaccess.services.blueprint.exception.InvalidDataException;
import com.fundsaccess.services.blueprint.port.adapter.api.rest.view.AllCurrenciesView;
import com.fundsaccess.services.blueprint.port.adapter.api.rest.view.ConvertedRateView;
import com.fundsaccess.services.blueprint.port.adapter.api.rest.view.EurfxRateView;
import com.fundsaccess.services.blueprint.port.adapter.api.rest.view.EurfxRateViewForDate;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * A sample REST resource.
 */
@Path("currencies")
@RequestScoped
public class BlueprintResource {

    @Inject
    EuroFxRateApplicationService euroFxRateApplicationService;

    /**
     * A sample rest endpoint. Returns an empty array.
     *
     * @return A collection of Available Currencies
     */
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvailableCurrencies() throws IOException {
       Set<String> availableCurrencies = euroFxRateApplicationService.getAllCurrencies(); 
        Collection<AllCurrenciesView> views = availableCurrencies.stream()
                .map(b -> new AllCurrenciesView(b))
                .collect(Collectors.toList());

        return Response.ok()
                .entity(views)
                .build();
    }
    
    @Path("/eurfxrate")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEurfxRateOfCurrencies(@QueryParam("currency") String currency) throws IOException {
    	ExceptionBuilder.throwExceptionIfDataNullOrEmpty("currency", currency);
    	 CompactData currencyData = euroFxRateApplicationService.getACurrencyData(currency);
    	 EurfxRateView views = new EurfxRateView(currencyData);
        return Response.ok()
                .entity(views)
                .build();    	
    }
    	
    @Path("/eurfxratedate")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEurfxRateOfCurrenciesOnDate(@QueryParam("date") String dateFromClient) throws IOException {
    	ExceptionBuilder.throwExceptionIfDataNullOrEmpty("date", dateFromClient);
    	ExceptionBuilder.throwExceptionIfInvalidDate(dateFromClient);
	
    	 Set<CurrencyDetails> availableCurrencies = euroFxRateApplicationService.getAllDataForAGivenDate(dateFromClient);
    	 Collection<EurfxRateViewForDate> views = availableCurrencies.stream().    			 
    	    	 map(b -> new EurfxRateViewForDate(b, dateFromClient)).
    	    	 collect(Collectors.toList());    	 
    	         return Response.ok()
    	                .entity(views)
    	                .build();
    }  
    
    @Path("/amountineuro")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCovertedAmountInEuro(@QueryParam("date") String dateFromClient, @QueryParam("currencyType") String currencyType, @QueryParam("amount") String amount) throws IOException {
    	
    	ExceptionBuilder.throwExceptionIfDataNullOrEmpty("date", dateFromClient);
    	ExceptionBuilder.throwExceptionIfInvalidDate(dateFromClient);
    	ExceptionBuilder.throwExceptionIfDataNullOrEmpty("currencyType", currencyType);
    	ExceptionBuilder.throwExceptionIfDataNullOrEmpty("amount", amount);
    	Double amountInNumbers = 0.0;
    	try {
    		 amountInNumbers = Double.parseDouble(amount); 
    	}
    	catch(NumberFormatException ex)
    	{
    		throw new InvalidDataException("Found non numeric value for parameter \"amount\"."+ex.getMessage());
    	}
    	Double covertedAmount = euroFxRateApplicationService.getConvertedExchangeRate(currencyType, dateFromClient, amountInNumbers);
    	ConvertedRateView views = new ConvertedRateView(covertedAmount, currencyType, dateFromClient);
       	 
       	         return Response.ok()
       	                .entity(views)
       	                .build();
    }   

}

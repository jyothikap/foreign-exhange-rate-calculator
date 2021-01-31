package com.fundsaccess.services.blueprint.port.adapter.api.rest;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fundsaccess.services.blueprint.application.BlueprintApplicationService;
import com.fundsaccess.services.blueprint.domain.model.CompactData;
import com.fundsaccess.services.blueprint.domain.model.JsonDateSerializer;
import com.fundsaccess.services.blueprint.port.adapter.api.rest.view.BlueprintView;
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
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * A sample REST resource.
 */
@Path("currencies")
@RequestScoped
public class BlueprintResource {

    @Inject
    BlueprintApplicationService blueprintApplicationService;

    /**
     * A sample rest endpoint. Returns an empty array.
     *
     * @return A collection of Available Currencies
     */
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvailableCurrencies() throws IOException {
    	
    	System.out.println("This method returns the list of all Currencies");

        Collection<CompactData> availableCurrencies = blueprintApplicationService.getAvailableCurrencies();
        
                                
        Collection<BlueprintView> views = availableCurrencies.stream()
                .map(b -> new BlueprintView(b))
                .collect(Collectors.toList());

        return Response.ok()
                .entity(views)
                .build();
    }
    
    @Path("/eurfxrate")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(using=JsonDateSerializer.class)
    public Response getEurfxRateOfCurrencies(@QueryParam("currency") String currency) throws IOException {
    	
    	System.out.println("This method returns the list of EURFX Rates for a particular currency....." +currency);
    	
    	 Collection<CompactData> availableCurrencies = blueprintApplicationService.getAvailableCurrencies();
      
//    	Collection<EurfxRateView> views = availableCurrencies.stream()
//                .map(b -> new EurfxRateView(b))
//                .collect(Collectors.toList());
    	 System.out.println("This method returns the list of EURFX Rates for a particular currency" +currency );
    	 Collection<EurfxRateView> views = availableCurrencies.stream().
    	 filter(c->c.dataSet.series.UNIT.equalsIgnoreCase(currency)).
    	 map(b -> new EurfxRateView(b)).
    	 collect(Collectors.toList());
     
        return Response.ok()
                .entity(views)
                .build();
    	
    }
    	
    @Path("/eurfxratedate")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEurfxRateOfCurrenciesOnDate(@QueryParam("date") String dateFromClient) throws IOException {
    	
    	System.out.println("This method returns the list of EURFX Rates for a particular date" + dateFromClient );
    	 Collection<CompactData> availableCurrencies = blueprintApplicationService.getAvailableCurrencies();
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
    	
    	System.out.println("date: "+dateFromClient);
    	System.out.println("currency type: 	"+ currencyType);
    	System.out.println("amount: 	"+ amount);
    	 Collection<CompactData> availableCurrencies = blueprintApplicationService.getAvailableCurrencies();
    	 availableCurrencies.removeIf((CompactData data)->!data.dataSet.series.UNIT.equalsIgnoreCase(currencyType));
    	 Collection<ConvertedRateView> views = availableCurrencies.stream().    			 
    	    	 map(b -> new ConvertedRateView(b, dateFromClient, amount, currencyType)).
    	    	 collect(Collectors.toList());
    	 
    	         return Response.ok()
    	                .entity(views)
    	                .build();
    }  

}

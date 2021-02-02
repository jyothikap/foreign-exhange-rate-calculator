package com.fundsaccess.services.blueprint.application;

import com.fundsaccess.services.blueprint.domain.model.CalculatorService;
import com.fundsaccess.services.blueprint.domain.model.CompactData;
import com.fundsaccess.services.blueprint.domain.model.CurrencyDetails;
import com.fundsaccess.services.blueprint.domain.model.Obs;
import com.fundsaccess.services.blueprint.exception.DataNotFoundException;
import com.fundsaccess.services.blueprint.exception.InvalidDataException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;

/**
 * Delegates and manages the business logic and transactions
 *
 * Sample code - delete or reuse as much as you like
 */
@ApplicationScoped
public class EuroFxRateApplicationService {

    @Transactional
    public Collection<CompactData> getAvailableCurrencies() throws IOException {
        Set<CompactData> currencyCollection = new HashSet<CompactData>();      
        {
            Set<String> allLinks =  getAllDownloadLinks();
            HtmlReader htmlReader = new HtmlReader();
            allLinks.forEach(s -> {
                try {
                	Document xmlDocument = htmlReader.readHtmlPage(s);
                    CompactData compactData = deserializeXmlData(xmlDocument);
                   currencyCollection.add(compactData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });             
        }
        return currencyCollection;
    }
   
    private Set<String> getAllDownloadLinks() throws IOException
    {
    	  HtmlReader htmlReader = new HtmlReader();
          Document htmlDoc = htmlReader.readHtmlPage("https://www.bundesbank.de/dynamic/action/de/statistiken/zeitreihen-datenbanken/zeitreihen-datenbank/759778/759778?listId=www_s331_b01012_3");
          Set<String> allLinks = htmlReader.extractLinks(htmlDoc,"fileformat=sdmx" );
           return allLinks;
    }
    
    private CompactData deserializeXmlData(Document xmlDocument) throws IOException
    {
    	return (CompactData) new SerializationService().deserializeXml(xmlDocument.toString(),
                CompactData.class);
    }
    
    private String  getCurrencyTagElement(Document xmlDocument)
    {
    	return xmlDocument.select("[^UNIT]").attr("UNIT");
    }
    
    public Set<String> getAllCurrencies() throws IOException
    {
    	Set<String> allDownloadLinks = getAllDownloadLinks();
    	Set<String> currencies = new HashSet<String>();
    	HtmlReader htmlReader = new HtmlReader();
    	allDownloadLinks.forEach(s -> {
             try {
                 Document doc = htmlReader.readHtmlPage(s);
                 String currency = getCurrencyTagElement(doc);
                 currencies.add(currency);
             } catch (IOException e) {
                 e.printStackTrace();
             }
         });
    	return currencies;
    }
    
    public CompactData getACurrencyData(String currencyFromClient) throws IOException
    {
    	Set<String> allDownloadLinks = getAllDownloadLinks();
    	HtmlReader htmlReader = new HtmlReader();
    	CompactData currencyData = null;
    	for(String downloadLink: allDownloadLinks)
    	{
    		try {
                Document doc = htmlReader.readHtmlPage(downloadLink);
                String currency = getCurrencyTagElement(doc);
                if(currencyFromClient.equalsIgnoreCase(currency))
                {
                	currencyData = deserializeXmlData(doc);
                	break;
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            }
    	}
    	if(currencyData == null)
    	{
    		 throw new InvalidDataException("Currency Not Found!!");
    	}
    	return currencyData;
    }
    
    public Double getConvertedExchangeRate(String currencyType, String dateFromClient, Double amountInNumbers) throws IOException
    {
    	CompactData currencyData = this.getACurrencyData(currencyType);
    	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );
    	Double result = 0.0;
    	for(Obs obsData: currencyData.dataSet.series.Obs)
		 {				
    			LocalDate dateFromClientParsed = LocalDate.parse(dateFromClient, dateFormatter );
	        	LocalDate dateFromDB = LocalDate.parse(obsData.timePeriod, dateFormatter );
	        	System.out.println("inside loop");
				if(dateFromClientParsed.compareTo(dateFromDB) == 0)
				{
					System.out.println("Found match");
					System.out.println("bbkObsStatus"+obsData.obsValue);
					if(!StringUtils.isEmpty(obsData.obsValue))
					{
						result = amountInNumbers / Double.parseDouble(obsData.obsValue);
						break;	
					}
					
				}
		 }
    	return result;
    }
    
    public Set<CurrencyDetails> getAllDataForAGivenDate(String dateStringFromClient) throws IOException {  
    	Set<String> allLinks =  getAllDownloadLinks();
    	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );
         HtmlReader htmlReader = new HtmlReader();
        Set<CurrencyDetails> currencyDetails = new HashSet<CurrencyDetails>();
         allLinks.forEach(s -> {
             try {
             	 	Document xmlDocument = htmlReader.readHtmlPage(s);
             	 	CompactData currencyData = deserializeXmlData(xmlDocument);
             	 	
             	 	Set<Obs> obsCollection = new HashSet<Obs>();
             	 	for(Obs obsData: currencyData.dataSet.series.Obs) {
             	 		LocalDate dateFromClientParsed = LocalDate.parse(dateStringFromClient, dateFormatter );
             	 		LocalDate dateFromDB = LocalDate.parse(obsData.timePeriod, dateFormatter );
             	 		System.out.println("inside loop");
             	 		if(dateFromClientParsed.compareTo(dateFromDB) == 0)
             	 		{
             	 			obsCollection.add(obsData);
             	 			break;
             	 		}
             	 	}
    				currencyDetails.add(new CurrencyDetails(currencyData.dataSet.series.currency, obsCollection));             	
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }); 
        return currencyDetails;
    }
   
    
}

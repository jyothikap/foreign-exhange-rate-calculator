package com.fundsaccess.services.blueprint.application;

import com.ctc.wstx.stax.WstxInputFactory;
import com.ctc.wstx.stax.WstxOutputFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fundsaccess.services.blueprint.domain.model.Blueprint;
import com.fundsaccess.services.blueprint.domain.model.CalculatorService;
import com.fundsaccess.services.blueprint.domain.model.CompactData;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.xml.stream.XMLInputFactory;

import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Delegates and manages the buisnesslogic and transactions
 *
 * Sample code - delete or reuse as much as you like
 */
@ApplicationScoped
public class BlueprintApplicationService {

    @Inject
    CalculatorService calculatorService;

    @Transactional
    public Collection<CompactData> getAvailableCurrencies() throws IOException {
        // Collection<Blueprint> blueprints = this.lookupBlueprints();
        // blueprints.forEach(this::enrich);

        Set<CompactData> currencyCollection = new HashSet<CompactData>();
        {
            HtmlScraper htmlScrapper = new HtmlScraper();
            Document htmlDoc = htmlScrapper.readHtmlPage("https://www.bundesbank.de/dynamic/action/de/statistiken/zeitreihen-datenbanken/zeitreihen-datenbank/759778/759778?listId=www_s331_b01012_3");
            Set<String> allLinks = htmlScrapper.extractLinks(htmlDoc,"fileformat=sdmx" );
            allLinks.forEach(s -> {
                try {
                	Document doc = htmlScrapper.readHtmlPage(s);
                    CompactData compactData = (CompactData) new SerializationService().deserializeXml(doc.toString(),
                            CompactData.class);
                   currencyCollection.add(compactData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });             
        }
        
        return currencyCollection;
    }

//    private void enrich(Blueprint blueprint) {
//        blueprint.changeValue(calculatorService.calculate(blueprint.name()));
//    }
//
//    private Collection<Blueprint> lookupBlueprints() {
//        return Arrays.asList(new Blueprint("Deep Thought"));
//    }
    
    // TO-DO
    
    
    
}

package com.fundsaccess.services.blueprint.application;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlReader {

	public Document readHtmlPage(String pageUrl) throws IOException {
        Document doc = Jsoup.connect(pageUrl)
                .data("query", "Java")
                .timeout(10000)
                .get();
        return doc;
    }
    
    
    //TO-DO 
    public Set<String> extractLinks(Document doc, String partialHref)
    {
        Set<String> links = new HashSet<>();
        Elements elements = doc.select("a[href]");
        for (Element element : elements) {
            if(element.attr("href").toLowerCase().contains(partialHref.toLowerCase()))
            {
                links.add(element.attr("href"));
            }
        }
        return links;
    }

}

package com.fundsaccess.services.blueprint.domain.model;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Series {
	@JacksonXmlProperty(isAttribute = true)
    public String UNIT;
    @JacksonXmlProperty(localName = "bbk:Obs")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Obs> Obs;
}

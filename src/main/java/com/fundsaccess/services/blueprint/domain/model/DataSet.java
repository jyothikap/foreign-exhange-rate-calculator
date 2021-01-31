package com.fundsaccess.services.blueprint.domain.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class DataSet {
	@JacksonXmlElementWrapper(localName = "bbk:Series")
    public Series series;
}

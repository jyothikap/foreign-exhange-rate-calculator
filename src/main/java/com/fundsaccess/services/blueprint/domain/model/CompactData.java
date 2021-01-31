package com.fundsaccess.services.blueprint.domain.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class CompactData {
	 @JacksonXmlElementWrapper(localName = "bbk:DataSet")
	    public DataSet dataSet;

}

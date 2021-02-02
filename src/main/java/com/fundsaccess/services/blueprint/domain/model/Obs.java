package com.fundsaccess.services.blueprint.domain.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Obs {
	@JacksonXmlProperty( localName = "TIME_PERIOD", isAttribute = true)
	public String timePeriod;
    @JacksonXmlProperty(localName = "OBS_VALUE", isAttribute = true)
    public String obsValue;
    @JacksonXmlProperty(localName = "BBK_DIFF", isAttribute = true)
    public String bbkDiff;
    @JacksonXmlProperty(localName = "BBK_OBS_STATUS", isAttribute = true)
    public String bbkObsStatus;
}

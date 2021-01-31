package com.fundsaccess.services.blueprint.domain.model;

import java.time.LocalDate;
import java.util.Date;

import javax.xml.bind.annotation.XmlSchemaType;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ext.CoreXMLSerializers.XMLGregorianCalendarSerializer;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Obs {
	@JacksonXmlProperty(isAttribute = true)
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	//@JsonSerialize(using=JsonDateSerializer.class)
	public String TIME_PERIOD;
    @JacksonXmlProperty(isAttribute = true)
    public String OBS_VALUE;
    @JacksonXmlProperty(isAttribute = true)
    public String BBK_DIFF;
    @JacksonXmlProperty(isAttribute = true)
    public String BBK_OBS_STATUS;
}

package com.fundsaccess.services.blueprint.application;

import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fundsaccess.services.blueprint.domain.model.CompactData;
import com.ctc.wstx.stax.WstxInputFactory;
import com.ctc.wstx.stax.WstxOutputFactory;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.xml.stream.XMLInputFactory;

public class SerializationService {

	public <T> Object deserializeXml(String xmlAsString, Class tClass) throws IOException {
		 XMLInputFactory input = new WstxInputFactory();
         input.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);

         ObjectMapper xmlMapper = new XmlMapper(new XmlFactory(input, new WstxOutputFactory()));
         xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        xmlMapper.setDateFormat(simpleDateFormat);
         // XmlMapper.configure(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
         //xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return xmlMapper.readValue(xmlAsString, CompactData.class);
	}
}

package com.fundsaccess.services.blueprint.domain.model;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.StdDateFormat;

public class JsonDateSerializer extends JsonSerializer<Date>  {
	  private static final DateFormat iso8601Format =
			    StdDateFormat.getDateInstance();

			  @Override
			  public void serialize(
			    Date date, JsonGenerator jgen, SerializerProvider provider)
			  throws IOException, JsonProcessingException {

			    // clone because DateFormat is not thread-safe
			    DateFormat myformat = (DateFormat) iso8601Format.clone();
			    String formattedDate = myformat.format(date);
			    jgen.writeString(formattedDate);
			    System.out.println(formattedDate);
			  }
}

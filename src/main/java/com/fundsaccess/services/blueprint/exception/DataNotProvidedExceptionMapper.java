package com.fundsaccess.services.blueprint.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fundsaccess.services.blueprint.domain.model.ErrorMessage;

@Provider
public class DataNotProvidedExceptionMapper implements ExceptionMapper<DataNotProvidedException>{

	@Override
	public Response toResponse(DataNotProvidedException ex) {
		ErrorMessage errorMsg = new ErrorMessage("Some of the parameters are empty or null. "+ex.getMessage(), 400);
		return Response.status(Status.BAD_REQUEST).
				entity(errorMsg).build();
	}
}

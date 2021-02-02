package com.fundsaccess.services.blueprint.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fundsaccess.services.blueprint.domain.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage errorMsg = new ErrorMessage("Request Invalid !! Currency code is invalid!", 400);
		return Response.status(Status.BAD_REQUEST).
				entity(errorMsg).build();
	}

}

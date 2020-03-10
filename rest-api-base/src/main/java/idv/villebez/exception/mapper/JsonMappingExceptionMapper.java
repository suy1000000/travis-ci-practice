package idv.villebez.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.JsonMappingException;

import idv.villebez.exception.ValidateException;
import idv.villebez.response.Warning;


/**
 * 當傳入的JSON參數發生對應錯誤的處理<br> 
 * 一律定義為400型請求錯誤
 * @see {@link Response.Status}
 */
@Provider
public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {
	
	@Override
	public Response toResponse(JsonMappingException exception) {
		return Response.status(Response.Status.BAD_REQUEST).entity(new Warning(ValidateException.Type.PARAMETER.getValue(), exception.getMessage())).build();
	}

}

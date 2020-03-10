package idv.villebez.exception.mapper;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;

import idv.villebez.response.Error;
import idv.villebez.util.RequestUtils;

/**
 * 內部錯誤時的對應處理器<br>
 * 當內部發生錯誤時仍能拋出JSON的回應內容<br>
 * 一律定義為500型內部伺服器錯誤
 * @see {@link Response.Status}
 */
@Provider
public class InternalExceptionMapper implements ExceptionMapper<Throwable> {
	private Logger log = Logger.getLogger(this.getClass());

	@Context
	private HttpServletRequest request;
	

	@Override
	public Response toResponse(Throwable exception) {
		if (!(exception instanceof WebApplicationException)) {
			try {
				log.error(RequestUtils.format(request).toString(), exception);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return Response.status(getStatusCode(exception)).entity(getEntity(exception)).build();
	}

	/*
	 * Get appropriate HTTP status code for an exception.
	 */
	private int getStatusCode(Throwable exception) {
		if (exception instanceof WebApplicationException) {
			return ((WebApplicationException) exception).getResponse().getStatus();
		}

		return Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
	}

	/*
	 * Get response body for an exception.
	 */
	private Object getEntity(Throwable exception) {
		return new Error(exception.getClass().getName(), exception.getMessage());
	}

}

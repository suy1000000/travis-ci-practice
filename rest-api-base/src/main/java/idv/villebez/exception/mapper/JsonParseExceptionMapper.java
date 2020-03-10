package idv.villebez.exception.mapper;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;

import idv.villebez.exception.ValidateException;
import idv.villebez.response.Error;
import idv.villebez.response.Warning;
import idv.villebez.util.RequestUtils;

/**
 * 當處理JSON參數發生轉型錯誤的處理<br> 
 * 若是外部傳入一律定義為400型請求錯誤<br>
 * 若是內部拋出則定義為500型內部伺服器錯誤
 * @see {@link Response.Status}
 */
@Provider
public class JsonParseExceptionMapper implements ExceptionMapper<JsonParseException> {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Context
	private HttpServletRequest request;

	@Override
	public Response toResponse(JsonParseException exception) {
		String stackTrace = ExceptionUtils.getStackTrace(exception);
		if (stackTrace.contains("idv.villebez.")) {
			try {
				log.error(RequestUtils.format(request).toString(), exception);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(new Error(exception.getClass().getName(), exception.getMessage())).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity(new Warning(ValidateException.Type.PARAMETER.getValue(), exception.getMessage())).build();
		}
	}

}

package idv.villebez.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.apache.commons.io.IOUtils;

import idv.villebez.exception.mapper.InternalExceptionMapper;
import idv.villebez.util.RequestUtils;

/**
 * 在request內填入資訊，讓程式出錯時 InternalExceptionMapper 能丟出更多的訊息
 * @see RequestUtils
 * @see InternalExceptionMapper
 */
@Provider
public class RequestWrapperFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if(requestContext.getEntityStream()!=null) {
			String jsonBody = IOUtils.toString(requestContext.getEntityStream());
			requestContext.setProperty("x-body", jsonBody);
			requestContext.setEntityStream(IOUtils.toInputStream(jsonBody));
		}
	}

}

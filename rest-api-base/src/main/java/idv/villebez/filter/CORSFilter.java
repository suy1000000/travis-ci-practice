package idv.villebez.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

/**
 * 定義CORS Policy(包含Allow-Headers, Allow-Origin, Allow-Methods)<br>
 * (在web.xml以參數形式被引入ServletContainer)
 */
@Provider
public class CORSFilter implements ContainerResponseFilter {

	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		MultivaluedMap<String, Object> headers = responseContext.getHeaders();
		headers.add("Access-Control-Allow-Origin", "http://petstore.swagger.io");
		headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH");
		headers.add("Access-Control-Allow-Headers", "Content-Type, Set-Cookie");
	}

}
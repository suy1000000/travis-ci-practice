package idv.villebez.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import idv.villebez.response.ApiResponse;

/**
 * 回傳內容包裝器，主要是把所有的回應都以ApiResponse包裝
 * @see {@link ApiResponse}
 */
@Provider
public class ResponseWrapperFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		// 排除不處理
		String path = requestContext.getUriInfo().getPath();
		if(path.startsWith("swagger") || path.startsWith("application")){
			return;
		}
		
		// 將成功的回傳都加上 response key
		if(responseContext.getStatusInfo().getFamily().equals(Status.Family.SUCCESSFUL) && responseContext.getEntity()!=null) {
			responseContext.setEntity(ApiResponse.success(responseContext.getEntity()));
		}
	}

}

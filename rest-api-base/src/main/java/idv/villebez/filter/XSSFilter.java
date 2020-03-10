package idv.villebez.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.Provider;

import org.apache.commons.collections.CollectionUtils;

import idv.villebez.util.XSSUtils;

/**
 * XSS攻擊過濾器<br>
 * 過濾XSS攻擊語法並確保HTML內容安全<br>
 * 目前實作：ESAPI過濾攻擊，JSOUP清除HTML
 */
@PreMatching
@Provider
public class XSSFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext request) throws IOException {
		cleanQueryParams( request );
        cleanHeaders( request.getHeaders() );
	}

	/**
     * Replace the existing query parameters with ones stripped of XSS vulnerabilities
     * @param request
     */
    private void cleanQueryParams( ContainerRequestContext request )
    {
        UriBuilder builder = request.getUriInfo().getRequestUriBuilder();
        MultivaluedMap<String, String> queries = request.getUriInfo().getQueryParameters();
 
        for( Map.Entry<String, List<String>> query : queries.entrySet() )
        {
            String key = query.getKey();
            List<String> values = query.getValue();
 
            List<String> xssValues = new ArrayList<String>();
            for( String value : values ) {
                xssValues.add( XSSUtils.stripXSS( value ) );
            }
 
            Integer size = CollectionUtils.size( xssValues );
            builder.replaceQueryParam( key );
 
            if( size == 1 ) {
                builder.replaceQueryParam( key, xssValues.get( 0 ) );
            } else if( size > 1 ) {
                builder.replaceQueryParam( key, xssValues.toArray() );
            }
        }
 
        request.setRequestUri( builder.build() );
    }
 
 
    /**
     * Replace the existing headers with ones stripped of XSS vulnerabilities
     * @param headers
     */
    private void cleanHeaders( MultivaluedMap<String, String> headers )
    {
        for( Map.Entry<String, List<String>> header : headers.entrySet() )
        {
            String key = header.getKey();
            List<String> values = header.getValue();
 
            List<String> cleanValues = new ArrayList<String>();
            for( String value : values ) {
                cleanValues.add( XSSUtils.stripXSS( value ) );
            }
 
            headers.put( key, cleanValues );
        }
    }
 
}

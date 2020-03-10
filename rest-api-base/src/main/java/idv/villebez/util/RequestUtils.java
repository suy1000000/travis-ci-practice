package idv.villebez.util;

import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestUtils {
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * 將 exception 物件內容轉成 jblog 錯誤信的格式
	 * 
	 * @param request
	 *            http-request 物件
	 * @param ex
	 *            excepiton 物件
	 * @return 錯誤信內容
	 * @throws JsonProcessingException 
	 */
	public static StringBuffer format(HttpServletRequest request) throws JsonProcessingException {
		StringBuffer sb = new StringBuffer();

		if (request != null) {

			StringBuffer paramString = new StringBuffer();
			Map<?, ?> paramMap = request.getParameterMap();
			if (paramMap != null) {
				for (Object key : paramMap.keySet()) {
					String keys = (String) key;
					paramString.append(keys + "=" + request.getParameter(keys) + "\n");
				}
			}

			String hostName = "";
			try {
				hostName += java.net.InetAddress.getLocalHost().getHostName();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			if (hostName.length() > 25) {
				hostName = hostName.substring(0, 25);
			}

			Cookie[] cookies = request.getCookies();
			String cookieList = "";
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					cookieList += cookie.getName() + "=" + cookie.getValue() + "\n";
				}
			}

			Map<String, String> headerData = new HashMap<String, String>();
			Enumeration<String> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				headerData.put(headerName, request.getHeader(headerName));
			}

			sb.append("<Header> \n").append(mapper.writeValueAsString(headerData)).append(" \n</Header> \n")
					.append("<Request_URI>")
					.append((request.getRequestURI() == null ? request.getRequestURL() : request.getRequestURI()))
					.append("</Request_URI> \n").append("<Query_String>")
					.append((request.getQueryString() == null ? "" : request.getQueryString()))
					.append("</Query_String> \n").append("<Body> \n")
					.append(request.getAttribute("x-body") == null ? "" : request.getAttribute("x-body"))
					.append(" \n</Body> \n").append("<FIELDNAMES> \n").append(paramString).append("</FIELDNAMES> \n")
					.append("<HTTP_Host>").append(hostName).append("</HTTP_Host> \n").append("<HTTP_ServerName>")
					.append(request.getServerName()
							+ (request.getServerPort() != 80 ? ":" + request.getServerPort() : ""))
					.append("</HTTP_ServerName> \n").append("<HTTP_Cookies> \n").append(cookieList)
					.append("</HTTP_Cookies> \n");
		}

		return sb;
	}

}

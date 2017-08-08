package com.org.readability.swagger;

import org.springframework.http.HttpHeaders;

/**
 * @author Kshitiz Garg
 */
public class ApiDocumentation{

	static final String CACHE_CONTROL_PARAM = "Cache-Control";

	static final String CACHE_CONTROL_PARAM_VALUE = "no-cache, no-store, must-revalidate";

	static final String PRAGMA_PARAM = "Pragma";

	static final String PRAGMA_PARAM_VALUE = "no-cache";

	static final String EXPIRES_PARAM = "Expires";

	static final String EXPIRES_PARAM_VALUE = "0";

	public static final String GET = "GET";
	public static final String POST = "POST";
	public static final String DELETE = "DELETE";

	public static final String LEARN_WORD = "api/learncomplexword";
	public static final String LEARN_WORD_API = "/"+LEARN_WORD;
	public static final String LEARN_WORD_POST = "Learns a complex word and returns readability metrics";
	
	ApiDocumentation(){
		
	}
	
	public static HttpHeaders noCacheHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(EXPIRES_PARAM, EXPIRES_PARAM_VALUE);
		headers.add(CACHE_CONTROL_PARAM, CACHE_CONTROL_PARAM_VALUE);
		headers.add(PRAGMA_PARAM, PRAGMA_PARAM_VALUE);
		return headers;
	}

}
package com.org.readability.endpoint.util;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Kshitiz Garg
 */
public class RequestTrackingInterceptor implements HandlerInterceptor {

	public static final String REQUEST_TRACK_ID = "requestTrackId";

	private static final Logger logger = LoggerFactory.getLogger(RequestTrackingInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)	throws Exception {
		String requestTrackId = httpServletRequest.getHeader(REQUEST_TRACK_ID);
		if (requestTrackId == null) {
			requestTrackId = UUID.randomUUID().toString();
			MDC.put(REQUEST_TRACK_ID, requestTrackId);
			logger.debug("Generated fresh requestTrackId: [{}] as it was not sent from upstream system", requestTrackId);
		} 
		else {
			MDC.put(REQUEST_TRACK_ID, requestTrackId);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
		// Nothing to do here
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)	throws Exception {		MDC.remove(REQUEST_TRACK_ID);
	}

}
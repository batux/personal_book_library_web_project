package com.personal.book.library.util;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {

	public static String getClientIP(HttpServletRequest httpServletRequest) {

		final String xfHeader = httpServletRequest.getHeader("X-Forwarded-For");

		if (xfHeader == null) {
			return httpServletRequest.getRemoteAddr();
		}

		return xfHeader.split(",")[0];
	}
}

package com.personal.book.library.util;

import javax.servlet.http.HttpSession;

import com.personal.book.library.servicelayer.model.UserSessionSummary;

public class HttpSessionUtil {

	public static Long getUserId(HttpSession httpSession) {
		
		Object userIdObj = httpSession.getAttribute(Constant.USER_ID);
		
		if(userIdObj == null) {
			return -1L;
		}
		
		return Long.parseLong(userIdObj.toString());
	}
	
	public static UserSessionSummary getUserSessionSummary(HttpSession httpSession) {
	
		Object userSummaryObj = httpSession.getAttribute(Constant.USER_SUMMARY);
		if(userSummaryObj == null) return null;
		return (UserSessionSummary) userSummaryObj;
	}
	
}

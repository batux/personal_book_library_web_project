package com.personal.book.library.util;

import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public class ReCaptchaUtil {

	private static Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");
	
	public static boolean responseSanityCheck(String response) {
        return StringUtils.hasLength(response) && RESPONSE_PATTERN.matcher(response).matches();
    }
}

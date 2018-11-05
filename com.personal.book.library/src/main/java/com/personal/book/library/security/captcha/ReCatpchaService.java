package com.personal.book.library.security.captcha;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.personal.book.library.servicelayer.exception.ReCaptchaInvalidFormatException;
import com.personal.book.library.servicelayer.exception.ReCaptchaInvalidValueException;
import com.personal.book.library.util.ReCaptchaUtil;

@Service
public class ReCatpchaService {

	@Value("${google.captcha.secret.key}")
	private String reCaptchaSecretKey;
	
	@Autowired
	private RestTemplate restTemplate;
	 
	public void processRecaptchaResponse(String response, String clientIp) {
		
		if(!ReCaptchaUtil.responseSanityCheck(response)) {
			throw new ReCaptchaInvalidFormatException("INVALID_RECAPTCHA_FORMAT", "Your g-captcha-response value has an invalid format!");
		}
		
		try {
			URI uri = URI.create(String.format("https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s&remoteip=%s",
					reCaptchaSecretKey, response, clientIp));
		
			ReCaptchaResponse reCaptchaResponse = restTemplate.getForObject(uri, ReCaptchaResponse.class);
			
			if (!reCaptchaResponse.isSuccess()) {
				throwReCaptchaInvalidValueException();
			}
			
		}
		catch(Exception e) {
			System.out.println("ReCatpchaService.processRecaptchaResponse" + e.getMessage());
			throwReCaptchaInvalidValueException();
		}
	}
	
	private void throwReCaptchaInvalidValueException() {
		throw new ReCaptchaInvalidValueException("INVALID_RECAPTCHA_VALUE", "Your g-captcha-response value is invalid!");
	}
}

package com.personal.book.library.servicelayer.exception;

public class ReCaptchaInvalidValueException extends ServiceException {

	private static final long serialVersionUID = 8440344428135425887L;

	public ReCaptchaInvalidValueException(String message, String detailedMessageDescription) {
		super(message, detailedMessageDescription);
	}
}

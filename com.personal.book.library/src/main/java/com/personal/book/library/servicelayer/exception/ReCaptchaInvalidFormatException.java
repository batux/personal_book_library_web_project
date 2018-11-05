package com.personal.book.library.servicelayer.exception;

public class ReCaptchaInvalidFormatException extends ServiceException {

	private static final long serialVersionUID = 8440344428135425887L;

	public ReCaptchaInvalidFormatException(String message, String detailedMessageDescription) {
		super(message, detailedMessageDescription);
	}
}

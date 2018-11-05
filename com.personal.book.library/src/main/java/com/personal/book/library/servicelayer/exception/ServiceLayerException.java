package com.personal.book.library.servicelayer.exception;

public class ServiceLayerException extends ServiceException {

	private static final long serialVersionUID = -7794235103266902351L;
	
	public ServiceLayerException(String message, String detailedMessageDescription) {
		super(message, detailedMessageDescription);
	}

}

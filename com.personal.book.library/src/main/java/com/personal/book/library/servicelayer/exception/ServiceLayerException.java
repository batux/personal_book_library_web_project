package com.personal.book.library.servicelayer.exception;

public class ServiceLayerException extends RuntimeException {

	private static final long serialVersionUID = -7794235103266902351L;

	private String message;
	
	private String detailedMessageDescription;
	
	public ServiceLayerException(String message, String detailedMessageDescription) {
		this.setMessage(message);
		this.setDetailedMessageDescription(detailedMessageDescription);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetailedMessageDescription() {
		return detailedMessageDescription;
	}

	public void setDetailedMessageDescription(String detailedMessageDescription) {
		this.detailedMessageDescription = detailedMessageDescription;
	}
}

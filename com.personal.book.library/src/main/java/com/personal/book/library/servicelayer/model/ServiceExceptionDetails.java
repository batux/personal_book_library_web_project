package com.personal.book.library.servicelayer.model;

public class ServiceExceptionDetails {

	private String errorMessage;
	
	private String detailedErrorMessage;
	
	private String errorURL;
	
	public ServiceExceptionDetails(String errorMessage, String detailedErrorMessage, String errorURL) {
		
		this.setErrorMessage(errorMessage);
		this.setDetailedErrorMessage(detailedErrorMessage);
		this.setErrorURL(errorURL);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getDetailedErrorMessage() {
		return detailedErrorMessage;
	}

	public void setDetailedErrorMessage(String detailedErrorMessage) {
		this.detailedErrorMessage = detailedErrorMessage;
	}

	public String getErrorURL() {
		return errorURL;
	}

	public void setErrorURL(String errorURL) {
		this.errorURL = errorURL;
	}
}

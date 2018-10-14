package com.personal.book.library.servicelayer.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class MailContext implements Serializable{

	private static final long serialVersionUID = 2418416540236857360L;
	private String title;
	private String body;
	private String toEmails;
	
	public MailContext() {
		this.setTitle(StringUtils.EMPTY);
		this.setBody(StringUtils.EMPTY);
		this.setToEmails(StringUtils.EMPTY);
	}
	
	public MailContext(String title, String body, String toEmails) {
		this.setTitle(title);
		this.setBody(body);
		this.setToEmails(toEmails);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getToEmails() {
		return toEmails;
	}
	public void setToEmails(String toEmails) {
		this.toEmails = toEmails;
	}
	
}

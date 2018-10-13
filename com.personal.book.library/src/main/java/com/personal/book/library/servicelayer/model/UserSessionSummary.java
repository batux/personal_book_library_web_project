package com.personal.book.library.servicelayer.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class UserSessionSummary implements Serializable{

	private static final long serialVersionUID = 4561721000059039325L;
	
	private String name;
	private String surname;
	
	public UserSessionSummary() {
		this.setName(StringUtils.EMPTY);
		this.setSurname(StringUtils.EMPTY);
	}
	
	public UserSessionSummary(String name, String userName) {
		this.setName(name);
		this.setSurname(userName);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
}

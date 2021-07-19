package com.webservice.rest.exception;

import java.util.Date;

public class NotFoundException {
	
	private Date date;
	private String message;
	private String details;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public NotFoundException(Date date, String details, String message) {
		super();
		this.date = date;
		this.details = details;
		this.message = message;
	}
	

}

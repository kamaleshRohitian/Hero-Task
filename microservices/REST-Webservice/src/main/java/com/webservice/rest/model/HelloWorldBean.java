package com.webservice.rest.model;

public class HelloWorldBean {
	
	public String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

	public HelloWorldBean(String msg)
	{
		this.message=msg;
	}
	
	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}

	
	

}

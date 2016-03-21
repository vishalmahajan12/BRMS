package com.sample;

import java.io.Serializable;

public class Email implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String body;
	String subject;
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Override
	public String toString() {
		return "Email [body=" + body + ", subject=" + subject + "]";
	}
	public Email(String body, String subject) {
		super();
		this.body = body;
		this.subject = subject;
	}
	
	
	
}

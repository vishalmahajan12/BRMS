package com.sample;

public class EmailService {

	public Email verify(Email email) {
		email.setBody(email.getBody() + " Body verified");
		email.setSubject(email.getSubject() + " Subject verified");
		System.out.println("Email :" + email);
		return email;
	}
}

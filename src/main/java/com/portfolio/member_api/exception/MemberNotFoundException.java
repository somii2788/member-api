package com.portfolio.member_api.exception;

public class MemberNotFoundException extends RuntimeException {

	public MemberNotFoundException(String message) {
		super(message);
	}
}

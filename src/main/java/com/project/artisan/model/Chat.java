package com.project.artisan.model;

public class Chat {
	private String message;
	private String fromLogin;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFromLogin() {
		return fromLogin;
	}
	public void setFromLogin(String fromLogin) {
		this.fromLogin = fromLogin;
	}
	public Chat(String message, String fromLogin) {
		super();
		this.message = message;
		this.fromLogin = fromLogin;
	}
	public Chat() {};

}

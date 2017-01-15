package com.codechallenge.dbserver.model;

public class User {
	
	private String userName;
	private String userLogin;
	private String userPassword;
	
	public User(){}
	
	public User(String userName, String userLogin, String userPassword) {
		super();
		this.userName = userName;
		this.userLogin = userLogin;
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}

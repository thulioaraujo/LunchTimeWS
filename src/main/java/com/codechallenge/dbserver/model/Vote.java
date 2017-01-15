package com.codechallenge.dbserver.model;

public class Vote {
	
	private int voteID;
	private String userUserName;
	private String restaurantName;
	
	public Vote(String userUserName, String restaurantName) {
		super();
		this.userUserName = userUserName;
		this.restaurantName = restaurantName;
	}

	public int getVoteID() {
		return voteID;
	}

	public void setVoteID(int voteID) {
		this.voteID = voteID;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getUserUserName() {
		return userUserName;
	}

	public void setUserUserName(String userUserName) {
		this.userUserName = userUserName;
	}
}

package com.codechallenge.dbserver.model;

public class Restaurant {
	
	private Integer restaurantId;
	private String restaurantName;
	private String restaurantAddress;
	private Double restaurantLatitude;
	private Double restaurantLongitude;
	
	public Restaurant(){}
	
	public Restaurant(String restaurantName, String restaurantAddress, Double restaurantLatitude, Double restaurantLongitude) {
		super();
		this.restaurantName = restaurantName;
		this.restaurantAddress = restaurantAddress;
		this.restaurantLatitude = restaurantLatitude;
		this.restaurantLongitude = restaurantLongitude;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantAddress() {
		return restaurantAddress;
	}

	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}

	public Double getRestaurantLatitude() {
		return restaurantLatitude;
	}

	public void setRestaurantLatitude(Double restaurantLatitude) {
		this.restaurantLatitude = restaurantLatitude;
	}

	public Double getRestaurantLongitude() {
		return restaurantLongitude;
	}

	public void setRestaurantLongitude(Double restaurantLongitude) {
		this.restaurantLongitude = restaurantLongitude;
	}
}

package com.lunchtime.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;

import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Represents the Restaurant object from the database
 * 
 * @author Thúlio Araújo (thuliolins@gmail.com)
 * @since 2017-01-24
 * @version	1.0
 */
@Entity
public class Restaurant implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "restaurantSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "RESTAURANT_SEQUENCE"),
			@Parameter(name = "initial_value", value = "1000"), @Parameter(name = "increment_size", value = "1") })
	@GeneratedValue(generator = "restaurantSequenceGenerator")
	private Long restaurantId;

	private String restaurantName;

	private String restaurantAddress;

	private Double restaurantLatitude;

	private Double restaurantLongitude;

	@Column(insertable = false, updatable = false, columnDefinition = "Date default CURRENT_DATE")
	@org.hibernate.annotations.Generated(value = GenerationTime.INSERT)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date creationDate;

	public Restaurant() {
	}

	public Restaurant(Long restaurantId, String restaurantName, String restaurantAddress, Double restaurantLatitude,
			Double restaurantLongitude) {
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantAddress = restaurantAddress;
		this.restaurantLatitude = restaurantLatitude;
		this.restaurantLongitude = restaurantLongitude;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Restaurant {" + "id=" + this.getRestaurantId() + ", name='" + this.getRestaurantName() + '\''
				+ ", addres='" + this.getRestaurantAddress() + '\'' + ", latitude='" + this.getRestaurantLatitude()
				+ '\'' + ", longitude='" + this.getRestaurantLongitude() + ", creation='"
				+ this.getCreationDate().toString() + "'}";
	}
}

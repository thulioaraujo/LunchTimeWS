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
 * Represents the object Customer from the database
 * 
 * @author Thúlio Araújo (thuliolins@gmail.com)
 * @since 2017-01-24
 * @version	1.0
 */
@Entity
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "customerSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "CUSTOMER_SEQUENCE"),
			@Parameter(name = "initial_value", value = "1000"), @Parameter(name = "increment_size", value = "1") })
	@GeneratedValue(generator = "customerSequenceGenerator")
	private Long customerId;

	private String customerName;

	@Column(unique = true)
	private String customerLogin;

	private String customerPassword;

	@Column(insertable = false, updatable = false, columnDefinition = "Date default CURRENT_DATE")
	@org.hibernate.annotations.Generated(value = GenerationTime.INSERT)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date creationDate;

	public Customer() {
	}

	public Customer(String customerName, String customerLogin, String customerPassword) {
		this.customerName = customerName;
		this.customerLogin = customerLogin;
		this.customerPassword = customerPassword;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerLogin() {
		return customerLogin;
	}

	public void setCustomerLogin(String customerLogin) {
		this.customerLogin = customerLogin;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Customer {" + "id=" + this.getCustomerId() + ", name='" + this.getCustomerName() + '\'' + ", login='"
				+ this.getCustomerLogin() + '\'' + ", creation='" + this.getCreationDate().toString() + "'}";
	}
}

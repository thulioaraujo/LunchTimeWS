package com.lunchtime.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Represents the object Vote from the database
 * 
 * @author Thúlio Araújo (thuliolins@gmail.com)
 * @since 2017-01-24
 * @version	1.0
 */
@Entity
public class Vote implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "voteSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "VOTE_SEQUENCE"),
			@Parameter(name = "initial_value", value = "1000"), @Parameter(name = "increment_size", value = "1") })
	@GeneratedValue(generator = "voteSequenceGenerator")
	private Long voteId;

	@ManyToOne(fetch = FetchType.EAGER)
	private Restaurant restaurantVote;

	@ManyToOne(fetch = FetchType.EAGER)
	private Customer customerVote;

	@Column(insertable = false, updatable = false, columnDefinition = "Date default CURRENT_DATE")
	@org.hibernate.annotations.Generated(value = GenerationTime.INSERT)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date creationDate;

	@Temporal(TemporalType.DATE)
	private Date voteDay;

	public Vote() {
	}

	public Vote(Long voteId, Restaurant restaurantVote, Customer userVote, Date voteDay) {
		super();
		this.voteId = voteId;
		this.restaurantVote = restaurantVote;
		this.customerVote = userVote;
		this.voteDay = voteDay;
	}

	public Long getVoteId() {
		return voteId;
	}

	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}

	public Restaurant getRestaurantVote() {
		return restaurantVote;
	}

	public void setRestaurantVote(Restaurant restaurantVote) {
		this.restaurantVote = restaurantVote;
	}

	public Customer getUserVote() {
		return customerVote;
	}

	public void setUserVote(Customer userVote) {
		this.customerVote = userVote;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getVoteDay() {
		return voteDay;
	}

	public void setVoteDay(Date voteDay) {
		this.voteDay = voteDay;
	}
}

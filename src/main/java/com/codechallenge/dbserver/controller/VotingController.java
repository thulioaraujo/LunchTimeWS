package com.codechallenge.dbserver.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codechallenge.dbserver.model.Vote;
import com.codechallenge.dbserver.util.Utitlity;

@Path("/voting_controller")
public class VotingController {

	// HTTP Get Method
	@GET
	// Path: http://<appln-folder-name>/voting_controller/doregister
	@Path("/dovote")
	// Produces JSON as response
	@Produces(MediaType.APPLICATION_JSON)
	// Query parameters are parameters:
	// http://<appln-folder-name>/voting_controller/doregister?user_name=xxxxx&restaurant_name=xxxx
	public String dovote(@QueryParam("user_username") String user_name, @QueryParam("restaurant_name") String restaurant_name) {
		String response = "";
		
		Vote vote = new Vote(user_name, restaurant_name);
		if (checkIfAlreadyVoted(vote) || checkIfRestaurantWasChoosedOnWeek(vote)) {
			return response = Utitlity.constructJSON("register_vote", false, "You already voted today.");
		}
		
		int retCode = registerVote(vote);

		if (retCode == 0) {
			response = Utitlity.constructJSON("register_vote", true);
		} else if (retCode == 1) {
			response = Utitlity.constructJSON("register_vote", false, "Error occured");
		}
		return response;
	}
	
	// HTTP Get Method
	@GET
	// Path: http://<appln-folder-name>/voting_controller/getdailyrestaurantvotes
	@Path("/getdailyrestaurantvotes")
	// Produces JSON as response
	@Produces(MediaType.APPLICATION_JSON)
	// Query parameters are parameters:
	// http://<appln-folder-name>/voting_controller/getdailyrestaurantvotes?restaurant_name=xxxx
	public String getdailyrestaurantvotes(@QueryParam("restaurant_name") String restaurant_name) {
		String response = "";
		int votes = getDailyRestaurantVotes(restaurant_name);
		response = Utitlity.constructJSON("votes", votes);
		return response;
	}
		
	// HTTP Get Method
	@GET
	// Path: http://<appln-folder-name>/voting_controller/getdailyrestaurantvotes
	@Path("/getmostvotedrestaurantperday")
	// Produces JSON as response
	@Produces(MediaType.APPLICATION_JSON)
	// Query parameters are parameters:
	// http://<appln-folder-name>/voting_controller/getdailyrestaurantvotes?restaurant_name=xxxx
	public String getmostvotedrestaurantperday() {
		String response = "";
		ArrayList<String> mostVotedRestaurant = getMostVotedRestaurantPerDay();
		if (mostVotedRestaurant.size() > 0) {			
			response = Utitlity.constructJSON(getMostVotedRestaurantPerDay());
		} else {
			response = Utitlity.constructJSON("register_vote", false, "Error occured");
		}
		return response;
	}
	
	private ArrayList<String> getMostVotedRestaurantPerDay(){
		ArrayList<String> mostVotedRestaurant = new ArrayList<String>();
		try {
			mostVotedRestaurant = VotingHelper.getMostVotedRestaurantPerDay();
		} catch (Exception e) {
			System.out.println("Inside getMostVotedRestaurantPerDay");
		}
		
		return mostVotedRestaurant;
	}
	
	private int getDailyRestaurantVotes(String restaurantName) {
		int result = 0;
		
		try {
			result = VotingHelper.getDailyRestaurantVotes(restaurantName);
		} catch (Exception e) {
			System.out.println("Inside getDailyRestaurantVotes");
		}
				
		return result;
	}
	
	private boolean checkIfAlreadyVoted(Vote vote) {
		boolean result = false;
		
		try {
			result = VotingHelper.checkIfAlreadyVoted(vote);
		} catch (Exception e) {
			System.out.println("Inside checkIfAlreadyVoted");
		}
		
		return result;
	}
	
	private boolean checkIfRestaurantWasChoosedOnWeek(Vote vote) {
		boolean result = false;
		
		try {
			result = VotingHelper.checkIfRestaurantWasChoosedOnWeek(vote);
		} catch (Exception e) {
			System.out.println("Inside checkIfAlreadyVoted");
		}
		
		return result;
	}
	
	private int registerVote(Vote vote) {
		int result = 1;
		if (Utitlity.isNotNull(vote.getUserUserName()) && Utitlity.isNotNull(vote.getRestaurantName())) {
			try {
				if (VotingHelper.insertVote(vote)) {
					result = 0;
				}
			} catch (SQLException sqle) {
				result = 1;
			} catch (Exception e) {
				result = 1;
			}
		} else {
			result = 1;
		}

		return result;
	}	
}

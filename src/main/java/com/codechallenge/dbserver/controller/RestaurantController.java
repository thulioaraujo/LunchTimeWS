package com.codechallenge.dbserver.controller;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codechallenge.dbserver.model.Restaurant;
import com.codechallenge.dbserver.util.Utitlity;

@Path("/restaurant_controller")
public class RestaurantController {

	// HTTP Get Method
	@GET
	// Path: http://<appln-folder-name>/restaurant_controller/doregister
	@Path("/doregister")
	// Produces JSON as response
	@Produces(MediaType.APPLICATION_JSON)
	// Query parameters are parameters:
	// http://<appln-folder-name>/restaurant_controller/doregister?name=xxxxx&address=xxxx&latitude=xxxx&longitude=xxxx
	public String doRegister(@QueryParam("name") String name, @QueryParam("address") String address,
			@QueryParam("latitude") double latitude, @QueryParam("longitude") double longitude) {
		String response = "";
		Restaurant restaurante = new Restaurant(name, address, latitude, longitude);
		int retCode = registerRestaurant(restaurante);

		if (retCode == 0) {
			response = Utitlity.constructJSON("register", true);
		} else if (retCode == 1) {
			response = Utitlity.constructJSON("register", false, "Restaurant already registered");
		} else if (retCode == 2) {
			response = Utitlity.constructJSON("register", false,
					"Special Characters are not allowed in name and address");
		} else if (retCode == 3) {
			response = Utitlity.constructJSON("register", false, "Error occured");
		}
		return response;
	}
	
	// HTTP Get Method
	@GET
	// Path: http://<appln-folder-name>/restaurant_controller/checkifexists
	@Path("/checkifexists")
	// Produces JSON as response
	@Produces(MediaType.APPLICATION_JSON)
	// Query parameters are parameters:
	// http://<appln-folder-name>/restaurant_controller/checkifexists?name=xxxxx
	public String doCheckIfExists(@QueryParam("name") String name) {
		String response = "";
		if (checkIfExists(name)) {
			response = Utitlity.constructJSON("restaurant", true);
		} else {
			response = Utitlity.constructJSON("restaurant", false, "Restaurant does not exists");
		}
		return response;
	}

	/**
	 * Method to check whether the restaurant exists
	 * 
	 * @param uname
	 * @param pwd
	 * @return
	 */
	private boolean checkIfExists(String uname) {
		System.out.println("Inside checkCredentials");
		boolean result = false;
		if (Utitlity.isNotNull(uname)) {
			try {
				result = RestaurantHelper.checkIfExists(uname);
			} catch (Exception e) {
				result = false;
			}
		} else {
			result = false;
		}

		return result;
	}
	
	private int registerRestaurant(Restaurant restaurant) {
		System.out.println("Inside checkCredentials");
		int result = 3;
		if (Utitlity.isNotNull(restaurant.getRestaurantName()) && Utitlity.isNotNull(restaurant.getRestaurantAddress())) {
			try {
				if (RestaurantHelper.insertRestaurant(restaurant)) {
					System.out.println("RegisterUSer if");
					result = 0;
				}
			} catch (SQLException sqle) {
				System.out.println("RegisterRestaurant catch sqle");
				// When Primary key violation occurs that means restaurant is already
				// registered
				if (sqle.getErrorCode() == 1062) {
					result = 1;
				}
				// When special characters are used in name, adress
				else if (sqle.getErrorCode() == 1064) {
					System.out.println(sqle.getErrorCode());
					result = 2;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Inside registerRestaurant catch e ");
				result = 3;
			}
		} else {
			System.out.println("Inside registerRestaurant else");
			result = 3;
		}

		return result;
	}	
}

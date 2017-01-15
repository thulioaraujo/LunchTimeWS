package com.codechallenge.dbserver.controller;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codechallenge.dbserver.model.User;
import com.codechallenge.dbserver.util.Utitlity;

@Path("/user_controller")
public class UserController {

	// HTTP Get Method
	@GET
	// Path: http://<appln-folder-name>/user_controller/doregister
	@Path("/doregister")
	// Produces JSON as response
	@Produces(MediaType.APPLICATION_JSON)
	// Query parameters are parameters:
	// http://<appln-folder-name>/user_controller/doregister?name=pqrs&username=abc&password=xyz
	public String doRegister(@QueryParam("name") String name, @QueryParam("username") String uname,
			@QueryParam("password") String pwd) {
		String response = "";
		
		User user = new User(name, uname, pwd);
		int retCode = registerUser(user);

		if (retCode == 0) {
			response = Utitlity.constructJSON("register", true);
		} else if (retCode == 1) {
			response = Utitlity.constructJSON("register", false, "You are already registered");
		} else if (retCode == 2) {
			response = Utitlity.constructJSON("register", false,
					"Special Characters are not allowed in Username and Password");
		} else if (retCode == 3) {
			response = Utitlity.constructJSON("register", false, "Error occured");
		}
		return response;
	}

	// HTTP Get Method
	@GET
	// Path: http://localhost/<appln-folder-name>/login/dologin
	@Path("/dologin")
	// Produces JSON as response
	@Produces(MediaType.APPLICATION_JSON)
	// Query parameters are parameters:
	// http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
	public String doLogin(@QueryParam("username") String uname, @QueryParam("password") String pwd) {
		String response = "";
		
		User user = checkCredentials(uname, pwd);
		
		if (user != null) {
			response = Utitlity.constructJSONLogin(user, true);
		} else {
			response = Utitlity.constructJSON("login", false, "Incorrect Email or Password");
		}
		return response;
	}

	private int registerUser(User user) {
		System.out.println("Inside checkCredentials");
		int result = 3;
		if (Utitlity.isNotNull(user.getUserName()) && Utitlity.isNotNull(user.getUserLogin())
				&& Utitlity.isNotNull(user.getUserPassword())) {
			try {
				if (UserHelper.insertUser(user)) {
					System.out.println("RegisterUSer if");
					result = 0;
				}
			} catch (SQLException sqle) {
				System.out.println("RegisterUSer catch sqle");
				// When Primary key violation occurs that means user is already
				// registered
				if (sqle.getErrorCode() == 1062) {
					result = 1;
				}
				// When special characters are used in name,username or password
				else if (sqle.getErrorCode() == 1064) {
					System.out.println(sqle.getErrorCode());
					result = 2;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Inside checkCredentials catch e ");
				result = 3;
			}
		} else {
			System.out.println("Inside checkCredentials else");
			result = 3;
		}

		return result;
	}

	/**
	 * Method to check whether the entered credential is valid
	 * 
	 * @param uname
	 * @param pwd
	 * @return
	 */
	private User checkCredentials(String uname, String pwd) {
		System.out.println("Inside checkCredentials");
		User result = null;
		if (Utitlity.isNotNull(uname) && Utitlity.isNotNull(pwd)) {
			try {
				result = UserHelper.checkLogin(uname, pwd);
			} catch (Exception e) {
				result = null;
			}
		} else {
			result = null;
		}

		return result;
	}	
}

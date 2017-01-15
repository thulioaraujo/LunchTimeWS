package com.codechallenge.dbserver.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.codechallenge.dbserver.model.Vote;
import com.codechallenge.dbserver.util.DBConnection;

public class VotingHelper {
	/**
	 * Method to vote
	 * 
	 * @param user_name
	 * @param restaurant_name
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public static boolean insertVote(Vote vote) throws SQLException, Exception {
		boolean insertStatus = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "INSERT into public.voting (user_username, restaurant_name) values('"+ vote.getUserUserName() + "',"+"'"
					+ vote.getRestaurantName() +"')";

			int records = stmt.executeUpdate(query);

			//When record is successfully inserted
			if (records > 0) {
				insertStatus = true;
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return insertStatus;
	}

	public static boolean checkIfAlreadyVoted(Vote vote) throws Exception {
		boolean isAlreadyVoted = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM public.voting WHERE user_username = '" + vote.getUserUserName() + "' AND vote_day = CURRENT_DATE";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				isAlreadyVoted = true;
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return isAlreadyVoted;
	}
	
	public static ArrayList<String> getMostVotedRestaurantPerDay() throws Exception {
		ArrayList<String> mostVotedRestaurant = new ArrayList<String>();
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT restaurant_name, count(restaurant_name) AS max_votes FROM public.voting WHERE vote_day = CURRENT_DATE GROUP BY restaurant_name ORDER BY restaurant_name LIMIT 1";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				mostVotedRestaurant.add(rs.getString("restaurant_name"));
				mostVotedRestaurant.add(String.valueOf(rs.getInt("max_votes")));
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}		
		return mostVotedRestaurant;
	}

	public static int getDailyRestaurantVotes(String restaurantName) throws Exception {
		int maxVotes = 0;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM public.voting WHERE restaurant_name = '" + restaurantName + "' AND vote_day = CURRENT_DATE";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				maxVotes++;
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}		
		return maxVotes;
	}

	public static boolean checkIfRestaurantWasChoosedOnWeek(Vote vote) throws Exception {
		boolean isAlreadyVoted = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM public.voting WHERE user_username = '" + vote.getUserUserName() + "' AND vote_day = CURRENT_DATE";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				isAlreadyVoted = true;
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return isAlreadyVoted;
	}
}

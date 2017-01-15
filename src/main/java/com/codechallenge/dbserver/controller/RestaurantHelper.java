package com.codechallenge.dbserver.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.codechallenge.dbserver.model.Restaurant;
import com.codechallenge.dbserver.util.DBConnection;

public class RestaurantHelper {
	/**
	 * Method to insert restaurant
	 * 
	 * @param name
	 * @param adress
	 * @param latitude
	 * @param longitude
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public static boolean insertRestaurant(Restaurant restaurant) throws SQLException, Exception {
		boolean insertStatus = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "INSERT into public.restaurant(name, address, latitude, longitude) values('"+ restaurant.getRestaurantName() + "',"+"'"
					+ restaurant.getRestaurantAddress() + "','" + restaurant.getRestaurantLatitude() + "','" + restaurant.getRestaurantLongitude() +"')";

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

	public static boolean checkIfExists(String restaurantName) throws Exception {
		boolean isRestaurantExists = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM public.restaurant WHERE name = '" + restaurantName + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				isRestaurantExists = true;
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
		return isRestaurantExists;
	}
}

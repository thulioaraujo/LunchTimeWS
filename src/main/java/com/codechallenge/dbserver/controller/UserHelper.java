package com.codechallenge.dbserver.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.codechallenge.dbserver.model.User;
import com.codechallenge.dbserver.util.DBConnection;

public class UserHelper {
	/**
	 * Method to insert uname and pwd in DB
	 * 
	 * @param name
	 * @param uname
	 * @param pwd
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public static boolean insertUser(User user) throws SQLException, Exception {
		boolean insertStatus = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "INSERT into public.user(name, username, password) values('"+user.getUserName() + "',"+"'"
					+ user.getUserLogin() + "','" + user.getUserPassword() + "')";

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
	
	/**
	 * Method to check whether uname and pwd combination are correct
	 * 
	 * @param uname
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public static User checkLogin(String uname, String pwd) throws Exception {
		User user = null;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT name, username, password FROM public.user WHERE username = '" + uname + "' AND password=" + "'" + pwd + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				user = new User(rs.getString("name"), 
						rs.getString("username"), 
						rs.getString("password"));
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
		return user;
	}
}

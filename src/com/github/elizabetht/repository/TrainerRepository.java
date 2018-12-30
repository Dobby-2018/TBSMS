package com.github.elizabetht.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.github.elizabetht.util.DbUtil;

public class TrainerRepository 
{
	private Connection dbConnection;

	public TrainerRepository() {
		dbConnection = DbUtil.getConnection();
	}

	public void save(String userName, String password, String firstName,
			String lastName, String dateOfBirth, String emailAddress) {
		if (dbConnection != null) {
			try {
				PreparedStatement prepStatement = dbConnection
						.prepareStatement("insert into trainer(userName, password, firstName, lastName, dateOfBirth, emailAddress) values (?, ?, ?, ?, ?, ?)");
				prepStatement.setString(1, userName);
				prepStatement.setString(2, password);
				prepStatement.setString(3, firstName);
				prepStatement.setString(4, lastName);				
				
				prepStatement.setDate(5, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy")
				.parse(dateOfBirth.substring(0, 10)).getTime()));

				prepStatement.setString(6, emailAddress);

				prepStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ParseException e) {				
				e.printStackTrace();
			}
		}
	}

	public boolean findByUserName(String userName) {
		if (dbConnection != null) {
			try {
				PreparedStatement prepStatement = dbConnection
						.prepareStatement("select count(*) from trainer where userName = ?");
				prepStatement.setString(1, userName);

				ResultSet result = prepStatement.executeQuery();
				if (result != null) {
					while (result.next()) {
						if (result.getInt(1) == 1) {
							return true;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean findByLogin(String userName, String password) {
		if (dbConnection != null) {
			try {
				PreparedStatement prepStatement = dbConnection
						.prepareStatement("select password from trainer where userName = ?");
				prepStatement.setString(1, userName);

				ResultSet result = prepStatement.executeQuery();
				if (result != null) {
					while (result.next()) {
						if (result.getString(1).equals(password)) {
							return true;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}


}

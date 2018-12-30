package com.github.elizabetht.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.github.elizabetht.model.Course;
import com.github.elizabetht.util.DbUtil;

public class CourseRepository 
{

	private Connection dbConnection;
	

	private Course course;

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void save(String courseName, int courseID,int duration, String CourseDescription) {
		if (dbConnection != null) {
			try {
				PreparedStatement prepStatement = dbConnection
						.prepareStatement("insert into course(courseID,courseName,duration,CourseDescription) values (?, ?, ?, ?)");
				prepStatement.setInt(1, courseID);
				prepStatement.setString(2, courseName);
				prepStatement.setInt(3, duration);
				prepStatement.setString(4, CourseDescription);				
				

				prepStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
	}

	public boolean findByCourseName(String courseName) {
		if (dbConnection != null) {
			try {
				PreparedStatement prepStatement = dbConnection
						.prepareStatement("select count(*) from course where courseName = ?");
				prepStatement.setString(1, courseName);

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

	public boolean findByName(String courseName) {
		if (dbConnection != null) {
			try {
				PreparedStatement prepStatement = dbConnection
						.prepareStatement("select CourseDescription from course where courseName = ?");
				prepStatement.setString(1, courseName);

				ResultSet result = prepStatement.executeQuery();
				if (result != null) {
					while (result.next()) {
						if (result.getString(1).equals(courseName)) {
							//fetching the description from the db and setting it directly into the bean field:
							
							course.setCourseDescription(result.getString("CourseDescription"));
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

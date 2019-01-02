package com.github.elizabetht.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.elizabetht.model.Course;
//import com.github.elizabetht.model.Course;
import com.github.elizabetht.util.DbUtil;

public class CourseRepository 
{

	private Connection dbConnection;
	private Course course;

	public CourseRepository()
	{
		dbConnection=DbUtil.getConnection();
		course=new Course();
	}
	/*public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}*/
	public void saveCourse(String courseName, int courseID,int duration, String CourseDescription)
	{
		
		if (dbConnection != null) {
			try {
				
				System.out.println("db connection formed");
				
				PreparedStatement prepStatement = dbConnection
						.prepareStatement("insert into course(CourseID,courseName,Duration,CourseDescription) values (?, ?, ?, ?)");
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

/*	public boolean findByCourseName(String courseName) {
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
	}*/

	public boolean findCourseByName(String courseName) {
		
		String crs,desc;
		if (dbConnection != null) {
			try {
				PreparedStatement prepStatement = dbConnection
						.prepareStatement("select courseName,CourseDescription from course where courseName = ?");
				prepStatement.setString(1, courseName);

				ResultSet result = prepStatement.executeQuery();
				if (result != null) {
					//crs=result.getString(1);
					while (result.next()) {
						
						crs=result.getString(1);
						if (crs.equals(courseName)) {
							//fetching the description from the db and setting it directly into the bean field:
							System.out.println("Description:"+result.getString(2));
							
							desc=result.getString(2);
							
							System.out.println("value of desc= "+desc);
							
							course.setCourseDescription(desc);
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

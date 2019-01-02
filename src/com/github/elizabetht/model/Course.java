package com.github.elizabetht.model;

public class Course 
{
	private int courseID;
	private String courseName;
	private int duration;
	private String CourseDescription;
	
	
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getCourseDescription() {
		return CourseDescription;
	}
	public void setCourseDescription(String CourseDescription) {
		
		System.out.println("inside the setter for courseDescription");
		this.CourseDescription = CourseDescription;
	}
	
}

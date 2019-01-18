package com.github.elizabetht.actions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;


import com.github.elizabetht.model.Course;
//import com.github.elizabetht.model.Course;
import com.github.elizabetht.service.CourseService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

/*It initializes the course list from Database and after course is selected 
 * displays the description as recorded in DB to the user. */

@SuppressWarnings("serial")
public class CourseAction extends ActionSupport {
	private String selectedCourse;
	private Set<String> availableCourse;
	private int selectedDuration;
	private List<Integer> availableDuration;
	
	/*private Course course;
	private CourseAction ca;*/

	public CourseAction() {

		System.out.println("inside the constructor for CourseAction");
		
		CourseService courseService = new CourseService();
		
		ArrayList<Course> c= courseService.coursesOffered();
		//Course c=new Course();
		
		availableCourse = new HashSet<String>(); // need to fetch courseNAmes from the DB into this list;
		
		for(Course name:c)
		{
		
		availableCourse.add(name.getCourseName());}

		
		
		/* hard coding up till DB problem is solved */
		
		
		
		
		availableDuration=new ArrayList<Integer>();
		availableDuration.add(4);
		availableDuration.add(6);
		

	}

	public Set<String> getAvailableCourse() {
		return availableCourse;
	}

	public void setAvailableCourse(Set<String> availableCourse) {
		System.out.println("setting the list");
		this.availableCourse = availableCourse;
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Select a course")
	public void setSelectedCourse(String selectedCourse) {
		System.out.println("inside the setter of selected course" +selectedCourse);
		this.selectedCourse = selectedCourse;
	}

	public String getSelectedCourse() {

		System.out.println("get of " + selectedCourse);
		return selectedCourse;
	}

	

/*	public CourseAction getCa() {
		return ca;
	}

	public void setCa(CourseAction ca) {
		
		System.out.println("setting the CourseAction obj");
		this.ca = ca;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		
		System.out.println("setting the Course obj");
		this.course = course;
	}*/

	public int getSelectedDuration() {
		
		System.out.println("inside getter  of duration"+ selectedDuration);
		return selectedDuration;
	}

	public void setSelectedDuration(int selectedDuration) {
		
		System.out.println("inside setter of duration"+selectedDuration);
		this.selectedDuration = selectedDuration;
	}

	public List<Integer> getAvailableDuration() {
		return availableDuration;
	}

	public void setAvailableDuration(List<Integer> availableDuration) {
		this.availableDuration = availableDuration;
	}

	/*@Action("course-input")
	public String input() throws Exception {
		return "course";
	}*/

	/*@Action(value = "course", results = { @Result(name = "success", location = "courseDetail", type = "redirect"),
			@Result(name = "failure", location = "course", type = "redirect") })*/
	@Override
	@Action("course")
	public String execute() throws Exception {
		
		System.out.println("inside the exceute method");

		String result = "";

		CourseService courseService = new CourseService();

		if (selectedCourse != null) {
			result = courseService.findByName(selectedCourse);
			if (result.equals("Course FoundSuccess")) {

				System.out.println("in success");
				return "success";
			} else {

				System.out.println("in failure");
				return "failure";
			}
		}
		return SUCCESS; // need to change as per logic
	}

}

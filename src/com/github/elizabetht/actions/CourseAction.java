package com.github.elizabetht.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.github.elizabetht.service.CourseService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

/*It initializes the course list from Database and after course is selected 
 * displays the description as recorded in DB to the user. */

@SuppressWarnings("serial")
public class CourseAction extends ActionSupport {
	private String selectedCourse;
	private List<String> availableCourse;

	@Action("course-input")
	public String input() throws Exception {
		return "course";
	}

	
	@Action(value = "course", results = {
			@Result(name = "success", location = "courseDetail", type = "redirect"),
			@Result(name = "failure", location = "course", type = "redirect")
			})
	@Override
	public String execute() throws Exception {

		String result = "";

		
		CourseService courseService = new CourseService();
		
		if (selectedCourse != null) {
			result = courseService.findByName(selectedCourse);
			if (result.equals("Course FoundSuccess")) {
				
				System.out.println("in success");
				return "success";
			} 
			else {
				
				System.out.println("in failure");
				return "failure";
			}
		}
		return SUCCESS; // need to change as per logic
	}

	public CourseAction() {

		availableCourse = new ArrayList<String>(); // need to fetch courseNAmes from the DB into this list;

		/* hard coding up till DB problem is solved */
		availableCourse.add("Java");
		availableCourse.add("Python");
		availableCourse.add("Big Data");
		availableCourse.add("Machine Learning");

	}

	public String getSelectedCourse() {
		return selectedCourse;
	}

	
	//@RequiredStringValidator(type = ValidatorType.FIELD, message = "Select a course")
	public void setSelectedCourse(String selectedCourse) {
		System.out.println(selectedCourse);
		this.selectedCourse = selectedCourse;
	}

	public List<String> getAvailableCourse() {
		return availableCourse;
	}

	public void setAvailableCourse(List<String> availableCourse) {
		this.availableCourse = availableCourse;
	}

}

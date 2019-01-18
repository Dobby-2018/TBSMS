package com.github.elizabetht.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;


@SuppressWarnings("serial")
public class TestCourseAction extends ActionSupport 
{
	
	private List<String> coursesToSelect;
	private String yourCourse;
	
	
	public TestCourseAction() 
	{
		
		coursesToSelect=new ArrayList<>();
		
		coursesToSelect.add("AWS");
		coursesToSelect.add("Azure");
		coursesToSelect.add("Hadoop");
	}


	public List<String> getCoursesToSelect() {
		return coursesToSelect;
	}


	public void setCoursesToSelect(List<String> coursesToSelect) {
		this.coursesToSelect = coursesToSelect;
	}


	public String getYourCourse() {
		return yourCourse;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Select a course")
	public void setYourCourse(String yourCourse) {
		this.yourCourse = yourCourse;
	}
	
	@Action("TestCourse")
	public String Test()
	{
		return "Test";
	}
	
	@Action(value = "Test", results = { @Result(name = "success", location = "CourseFound", type = "redirect"),
			@Result(name = "failure", location = "courseNotFound", type = "redirect"),
			@Result(name="input",location = "courseNotFound", type = "redirect")})
	@Override
	public String execute() throws Exception {
		
		System.out.println("value of selection:"+yourCourse);
		if (yourCourse != null) {
			return SUCCESS;
		}
		return "failure";
	}
	
	
	
}

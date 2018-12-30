package com.github.elizabetht.service;

import com.github.elizabetht.repository.CourseRepository;


public class CourseService 
{
	private CourseRepository courseRepository;
	//private String courseDescription;
	
	public CourseService() {
		courseRepository = new CourseRepository();
	}

	//Entering new course--- Only present in view rendered for the admin
	public String save(String courseName, int courseID, int duration, String discription) {
		if (courseRepository != null) {
			if (courseRepository.findByName(courseName)) {
				return "Course already Exists";
			}
			courseRepository.save(courseName, courseID, duration,discription);
			return "CourseEntrySuccess";
		} else {
			return "CourseEntryFailure";
		}
	}

	public String findByName(String courseName) {
		String result = "CourseNotFoundFailure";
		if (courseRepository != null) {
			boolean status = courseRepository.findByName(courseName);
			if (status) {
				result = "Course FoundSuccess";
						
				return result;
			}
		}
		return result;
	}
}

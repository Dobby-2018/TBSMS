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
			
			
			if (courseRepository.findCourseByName(courseName)) {
				
				System.out.println("course already exists");
				return "Course already Exists";
			}
			courseRepository.saveCourse(courseName, courseID, duration,discription);
			System.out.println("Course entered into the records");
			return "CourseEntrySuccess";
		} else {
			System.out.println("Course can't be entered into db");
			return "CourseEntryFailure";
		}
	}

	public String findByName(String courseName) {
		String result = "CourseNotFoundFailure";
		if (courseRepository != null) {
			System.out.println("Checking the course" + result);
			
			boolean status = courseRepository.findCourseByName(courseName);
			if (status) {
				
				System.out.println("Course found");
				result = "Course FoundSuccess";
						
				return result;
			}
		}
		System.out.println(result);
		return result;
	}
	
	
	//Test stub
	public static void main(String [] args)
	{
		CourseService cs=new CourseService();
		//cs.save("Python",3, 40, "Regular batch");
		cs.findByName("Python");
	
	}
}

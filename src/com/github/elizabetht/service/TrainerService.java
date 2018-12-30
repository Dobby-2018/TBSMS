package com.github.elizabetht.service;

import com.github.elizabetht.repository.TrainerRepository;

public class TrainerService
{
	
	private TrainerRepository trainerRepository;

	public TrainerService() {
		trainerRepository = new TrainerRepository();
	}

	public String save(String userName, String password,
			String firstName, String lastName, String dateOfBirth,
			String emailAddress) {
		if (trainerRepository != null) {
			if (trainerRepository.findByUserName(userName)) {
				return "SignupFailure-UserNameExists";
			}
			trainerRepository.save(userName, password, firstName, lastName,
					dateOfBirth, emailAddress);
			return "SignupSuccess";
		} else {
			return "SignupFailure";
		}
	}

	public String findByLogin(String userName, String password) {
		String result = "LoginFailure";
		if (trainerRepository != null) {
			boolean status = trainerRepository.findByLogin(userName, password);
			if (status) {
				result = "LoginSuccess";
			}
		}
		return result;
	}
}

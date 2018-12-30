package com.github.elizabetht.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;

import com.github.elizabetht.service.StudentService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport {

	private String pageName;
	private String userName;
	private String password;
	private List<String> userRole;
	private String selectedRole;

	@Action("login-input")
	public String input() throws Exception {
		return "login";
	}

	@Action("login")
	public String execute() throws Exception {
		String result = "";
		StudentService studentService = new StudentService();

		if (pageName != null && studentService != null) {
			if (pageName.equals("login")) {
				result = studentService.findByLogin(userName, password);
				if (result.equals("LoginFailure")) {
					return "failure";
				} else {
					return "success";
				}
			}
		}
		return SUCCESS;
	}

	public List<String> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<String> userRole) {
		this.userRole = userRole;
	}

	

	public LoginAction() {
		userRole=new ArrayList<String>();
		userRole.add("Student");
		userRole.add("Trainer");
		userRole.add("Admin");
	}

	public String getSelectedRole() {
		return selectedRole;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "User Type is a required field")
	public void setSelectedRole(String selectedRole) {
		this.selectedRole = selectedRole;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getUserName() {
		return userName;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "UserName is a required field")
	@StringLengthFieldValidator(type = ValidatorType.FIELD, maxLength = "12", minLength = "6", message = "UserName must be of length between 6 and 12")
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Password is a required field")
	@StringLengthFieldValidator(type = ValidatorType.FIELD, maxLength = "12", minLength = "6", message = "Password must be of length between 6 and 12")
	public void setPassword(String password) {
		this.password = password;
	}

}

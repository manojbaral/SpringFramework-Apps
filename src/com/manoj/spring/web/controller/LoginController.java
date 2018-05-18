package com.manoj.spring.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.manoj.spring.web.dao.User;
import com.manoj.spring.web.service.UsersService;

@Controller
public class LoginController {

	private UsersService usersService;

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	// Method for creating login page for user
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	// Method for creating login page for user
	@RequestMapping("/loggedout")
	public String showLoggedOut() {
		return "loggedout";
	}

	// Method for creating new account for user
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user", new User());
		return "newaccount";
	}

	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String createAccount(@Valid User user, BindingResult result) {

		if (result.hasErrors()) {

			return "newaccount";

		}

		user.setAuthority("user");
		user.setEnabled(true);

		if (usersService.exists(user.getUsername())) {

			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";

		}

		try {
			usersService.create(user);

		} catch (DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}

		return "accountcreated";

	}
}

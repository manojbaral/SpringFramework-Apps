package com.manoj.spring.web.dao;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.manoj.spring.web.validation.ValidEmail;

public class User {

	@NotBlank
	@Size(min=8,max=15)
	@Pattern(regexp="^\\w{8,}$")
	private String username;
	
	@NotBlank
	@Pattern(regexp="^\\S+$")
	@Size(min=8,max=15)
	private String password;
	
	@ValidEmail
	private String email;
	private boolean enabled = false;
	private String authority;

	// Default Constructor
	public User() {

	}

	public User(String username, String password, boolean enabled, String authority,String email) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.authority = authority;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}

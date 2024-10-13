package com.example.TimesheetManagement.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {
	
	private Employee user;
	
	public Integer getId() {
        return user.getId(); // Expose the employee's ID
    }

	public UserPrincipal(Employee user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String role = "ROLE_" + user.getRole(); // Prepend 'ROLE_' to the role
	    return Collections.singleton(new SimpleGrantedAuthority(role));
	}

	@Override
	public String getPassword() {	
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getEmail();
	}

}

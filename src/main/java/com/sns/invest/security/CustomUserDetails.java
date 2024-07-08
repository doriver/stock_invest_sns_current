package com.sns.invest.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sns.invest.user.model.UserJpa;

public class CustomUserDetails implements UserDetails {

	private final UserJpa user;
	
	public CustomUserDetails(UserJpa user) {
		this.user = user;
	}

	public int getId() {
		return user.getId();
	}

	public String getNickName() {
		return user.getNickName();
	}

	public String getEmail() {
		return user.getEmail();
	}
	public String getProfileImage() {
		return user.getProfileImage();
	}
	public String getProfileStatusMessage() {
		return user.getProfileStatusMessage();
	}
	public String getLocation() {
		return user.getLocation();
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}

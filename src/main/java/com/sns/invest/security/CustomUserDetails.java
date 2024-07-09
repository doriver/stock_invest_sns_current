package com.sns.invest.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.sns.invest.user.model.UserJpa;

public class CustomUserDetails implements UserDetails {

	private final UserJpa user;
	
	private final Collection<GrantedAuthority> authorities;
	
	public CustomUserDetails(UserJpa user, Collection<GrantedAuthority> authorities) {
		this.user = user;
		this.authorities = authorities;
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
        return authorities;
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

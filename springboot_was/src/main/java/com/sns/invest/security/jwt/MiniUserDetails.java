package com.sns.invest.security.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MiniUserDetails implements UserDetails {

	private final Collection<? extends GrantedAuthority> authorities;
	private final int userId;
	private final String userNickName;
	
	public MiniUserDetails(int userId, String userNickName, Collection<? extends GrantedAuthority> authorities) {
		this.userId = userId;
		this.userNickName = userNickName;
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public int getId() {
		return userId;
	}

	public String getNickName() {
		return userNickName;
	}
	
	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}

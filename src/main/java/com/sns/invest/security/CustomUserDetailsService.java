package com.sns.invest.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sns.invest.user.bo.UserBO;
import com.sns.invest.user.model.UserJpa;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserBO userBO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserJpa user = userBO.signIn(username);
		
		if (user == null) {			
			return null;
		}
		
		return new CustomUserDetails(user);
	}

}

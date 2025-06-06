package com.sns.invest.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sns.invest.user.bo.UserBO;
import com.sns.invest.user.dao.UserRepository;
import com.sns.invest.user.dao.model.Role;
import com.sns.invest.user.dao.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if (user == null) {			
			return null;
		}
		
		Role role = user.getRole();
		
		Collection<GrantedAuthority> authorities = new ArrayList();		

		authorities.add(new SimpleGrantedAuthority(role.getValue()));			

		
		return new CustomUserDetails(user, authorities);
	}

}

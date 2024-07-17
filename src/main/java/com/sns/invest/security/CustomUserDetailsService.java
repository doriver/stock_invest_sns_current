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
import com.sns.invest.user.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserBO userBO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userBO.signIn(username);
		
		if (user == null) {			
			return null;
		}
		
		String role = user.getRole();
		Collection<GrantedAuthority> authorities = new ArrayList();		
		
		if (role.equals("user") ) {
			authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));			
		} else if (role.equals("master")) {
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
		}
		
		return new CustomUserDetails(user, authorities);
	}

}

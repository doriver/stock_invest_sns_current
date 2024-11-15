package com.sns.invest.user.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sns.invest.user.dao.UserRepository;
import com.sns.invest.user.model.User;

import lombok.RequiredArgsConstructor;

import com.sns.invest.common.EncryptUtils;
import com.sns.invest.common.FileManagerService;
import com.sns.invest.security.jwt.JwtToken;
import com.sns.invest.security.jwt.JwtTokenProvider;

@Service
@RequiredArgsConstructor // final이 붙은 필드를 모아서 생성자를 만들어줌
public class UserBO {
	
	private final UserRepository userRepository; 
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final JwtTokenProvider jwtTokenProvider;
	
	public boolean isDuplicateId(String username) {
		if(userRepository.countByUsername(username) == 0) {
			return false;
		} else {
			return true;
		}
		
	}
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public JwtToken signIn(String username, String password) {
		// 1. username + password 를 기반으로 Authentication 객체 생성, 이때 인증여부를 확인하는 authenticated값이 false
    	UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
    	
    	// 2. username, password에 대한 검증, 메서드authenticate가 실행될 때 CustomUserDetailsService에서 만든 메서드loadUserByUsername 실행
    	Authentication authentication = authenticationManagerBuilder.getObject()
    												.authenticate(authenticationToken);
    				// UsernamePasswordAuthenticationToken임 안에principal에 CustomUserDetails들어있음   	
    	// 3. 인증 정보를 기반으로 JWT 토큰 생성
    	JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);   	
    	return jwtToken;
	}
	
	
	
	@Transactional
	public int signUp(String loginId, String password, String nickName, String email) {
			
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encryptPassword = passwordEncoder.encode(password);	
		
		int result = 0;
		
		if(encryptPassword.equals("")) {
			logger.error("[UserBO signUP] 암호화 실패!!!!!!!!!!!!!!");
			return result;
		}
		
		User user = User.builder()
				.username(loginId).password(encryptPassword)
				.nickName(nickName).email(email).role("user")
				.build();
		
		try { // save는 이런식으로 처리해야하는듯 , @Transactional 추가해줘야하는거 생각해야함
			if ( userRepository.save(user) instanceof User ) {
				result = 1;
				logger.info("회원가입 성공");
			}
		} catch (Exception e) {
			logger.error("[UserBO signUp] save()실패");
	        throw e;
	    }
		
		return result;
	}
	
	
	
	public User userInformation(int userId) {
		return userRepository.findById(userId);
	}
	
	@Transactional
	public int editLocation(int userId, String location) {
		
		int result = -1;
		
		User user = userRepository.findById(userId);
		
		if (user != null) {
			user.updateLocation(location);
			result = 1;
		}
		
		return result;
	}

	@Transactional
	public int editProfile(int userId, MultipartFile file, String profileStatusMessage) {
		
		int result = -1;
		
		String filePath = null;
		
		if(file != null) {
			FileManagerService fileManager = new FileManagerService();
			
			filePath = fileManager.saveFile(userId, file);
			
			if(filePath == null) {
				return result;
			}	
		}
		
		User user = userRepository.findById(userId);
		
		if (user != null) {
			user.updateProfileStatusMessage(profileStatusMessage);
			user.updateProfileImage(filePath);
			result = 1;
		}
		
		return result;
	}
	
	public String getProfileImage(int userId) {
		return userRepository.findProfileImageById(userId);
	}

	public String getlocation(int myUserId) {
		return userRepository.findLocationById(myUserId);
	}
}

package com.sns.invest.user.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.invest.user.dao.UserDAO;
import com.sns.invest.user.dao.UserRepository;
import com.sns.invest.user.model.User;
import com.sns.invest.user.model.UserJpa;

import lombok.RequiredArgsConstructor;

import com.sns.invest.common.EncryptUtils;
import com.sns.invest.common.FileManagerService;

@Service
@RequiredArgsConstructor // final이 붙은 필드를 모아서 생성자를 만들어줌
public class UserBO {
	@Autowired
	private UserDAO userDAO;
	
	private final UserRepository userRepository; 
	
	public boolean isDuplicateId(String username) {
		if(userRepository.countByUsername(username) == 0) {
			return false;
		} else {
			return true;
		}
		
		// return (userDAO.selectCountById(loginId) != 0);
	}
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int signUp(String loginId, String password, String nickName, String email) {
			
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encryptPassword = passwordEncoder.encode(password);	
		
		int result = 0;
		
		if(encryptPassword.equals("")) {
			logger.error("[UserBO signUP] 암호화 실패!!!!!!!!!!!!!!");
			return result;
		}
		
		UserJpa user = UserJpa.builder()
				.username(loginId).password(encryptPassword)
				.nickName(nickName).email(email).role("user")
				.build();
		
		try {
			if ( userRepository.save(user) instanceof UserJpa ) {
				result = 1;
				logger.info("회원가입 성공");
			}
		} catch (Exception e) {
			logger.error("[UserBO signUp] save()실패");
	        throw e; // 예외를 다시 던지거나 적절히 처리
	    }
		
		return result;
	}
	
	public UserJpa signIn(String username) {
		return userRepository.findByUsername(username);
	}
	
	public UserJpa userInformation(int userId) {
		return userRepository.findById(userId);
	}
	
	public int editLocation(int userId, String location) {
		return userDAO.updateUserLocation(userId, location);
	}

	public int editProfile(int userId, MultipartFile file, String profileStatusMessage) {
		
		String filePath = null;
		
		if(file != null) {
			FileManagerService fileManager = new FileManagerService();
			
			filePath = fileManager.saveFile(userId, file);
			
			if(filePath == null) {
				return -1;
			}	
		}
//		FileManagerService fileManager = new FileManagerService();
//		이건 파일첨부 필수였을때
//		String filePath = fileManager.saveFile(userId, file);
//		
//		if(filePath == null) {
//			return -1;
//		}
	
		return userDAO.updateUserProfile(userId, profileStatusMessage, filePath);
	}
	
	public String getProfileImage(int userId) {
		return userRepository.findProfileImageById(userId);
	}

	public String getlocation(int myUserId) {
		return userDAO.selectUserLocation(myUserId);
	}
}

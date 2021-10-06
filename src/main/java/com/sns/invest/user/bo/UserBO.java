package com.sns.invest.user.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.invest.user.dao.UserDAO;
import com.sns.invest.user.model.User;
import com.sns.invest.common.EncryptUtils;
import com.sns.invest.common.FileManagerService;

@Service
public class UserBO {
	@Autowired
	private UserDAO userDAO;
	
	public boolean isDuplicateId(String loginId) {
		if(userDAO.selectCountById(loginId) == 0) {
			return false;
		} else {
			return true;
		}
		
		// return (userDAO.selectCountById(loginId) != 0);
	}
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int signUp(String loginId, String password, String nickName, String email) {
		
		String encryptPassword = EncryptUtils.md5(password);	
		
		if(encryptPassword.equals("")) {
			logger.error("[UserBO signUP] 암호화 실패!!!!!!!!!!!!!!");
			return 0;
		}
		
		return userDAO.insertUser(loginId, encryptPassword, nickName, email);
	}
	
	public User signIn(String idForLogin, String passwordForLogin) {
		// 비밀번호를 암호화 하고 DAO 로 전달한다. 
		String encryptPassword = EncryptUtils.md5(passwordForLogin);
		
		return userDAO.selectUserByIdPassword(idForLogin, encryptPassword);
	}
	
	public User userInformation(int userId) {
		return userDAO.selectUserByUserId(userId);
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
		return userDAO.selectUserProfileImage(userId);
	}

	public String getlocation(int myUserId) {
		return userDAO.selectUserLocation(myUserId);
	}
}

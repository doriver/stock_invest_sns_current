package com.sns.invest.user.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.invest.user.dao.UserDAO;
import com.sns.invest.user.model.User;
import com.sns.invest.common.EncryptUtils;

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
}

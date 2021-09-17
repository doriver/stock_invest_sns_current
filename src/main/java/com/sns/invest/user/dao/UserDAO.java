package com.sns.invest.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.invest.user.model.User;

@Repository
public interface UserDAO {

	public int selectCountById(@Param("loginId") String loginId);

	public int insertUser(
			@Param("loginId") String loginId 
			, @Param("password") String password
			, @Param("nickName") String nickName
			, @Param("email") String email);
	
	public User selectUserByIdPassword(
			@Param("idForLogin") String idForLogin
			, @Param("passwordForLogin") String passwordForLogin);
	
}

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

	public User selectUserByUserId(@Param("userId") int userId);
	
	public int updateUserLocation(
			@Param("userId") int userId
			, @Param("location") String location);

	public int updateUserProfile(
			@Param("userId") int userId
			, @Param("profileStatusMessage") String profileStatusMessage
			, @Param("filePath") String filePath);
	
	public String selectUserProfileImage(
			@Param("userId") int userId);

	public String selectUserLocation(
			@Param("userId") int myUserId);
}

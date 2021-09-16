package com.sns.invest.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

	public int selectCountById(@Param("loginId") String loginId);

	public int insertUser(
			@Param("loginId") String loginId 
			, @Param("password") String password
			, @Param("nickName") String nickName
			, @Param("email") String email);
	
	
}

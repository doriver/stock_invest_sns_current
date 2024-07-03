package com.sns.invest.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sns.invest.user.model.UserJpa;

public interface UserRepository extends JpaRepository<UserJpa, Integer>{
	long countByLoginId(String loginId); // 아이디 중복체크
	UserJpa findByLoginIdAndPassword(String loginId, String password); // 로그인
	
    @Query("SELECT u.profileImage FROM User u WHERE u.id = :userId")
    String findProfileImageById(int userId);
}

package com.sns.invest.user.dao;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sns.invest.user.model.UserJpa;

public interface UserRepository extends JpaRepository<UserJpa, Integer>{
	long countByUsername(String username); // 아이디 중복체크
	UserJpa findByUsername(String username); // 로그인
	
	@Query("SELECT profileImage FROM UserJpa  WHERE id = :id") // From뒤에 테이블 이름이 아니라 entity이름 와야함
    String findProfileImageById(@Param("id") int id);
	
	UserJpa findById(int id);
}

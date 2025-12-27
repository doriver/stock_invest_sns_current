package com.sns.invest.user.dao;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sns.invest.user.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	long countByUsername(String username); // 아이디 중복체크
	User findByUsername(String username); // 로그인
	User findById(int id);
	
	@Query("SELECT profileImage FROM User WHERE id = :id") // From뒤에 테이블 이름이 아니라 entity이름 와야함
    String findProfileImageById(@Param("id") int id);

	@Query("SELECT location FROM User WHERE id = :id") // From뒤에 테이블 이름이 아니라 entity이름 와야함
	String findLocationById(@Param("id") int id);
	
}

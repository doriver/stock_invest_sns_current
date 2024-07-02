package com.sns.invest.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.invest.user.model.UserJpa;

public interface UserRepository extends JpaRepository<UserJpa, Integer>{

}

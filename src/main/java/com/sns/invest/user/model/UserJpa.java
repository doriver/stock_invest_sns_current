package com.sns.invest.user.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserJpa {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false, length = 16)
    private String username;

    @Column(name = "password", nullable = false, length = 128)
    private String password;

    @Column(name = "nickName", nullable = false, length = 16)
    private String nickName;

    @Column(name = "email", nullable = false, length = 64)
    private String email;

    @Column(name = "profileImage", length = 128)
    private String profileImage;

    @Column(name = "profileStatusMessage", length = 128)
    private String profileStatusMessage;

    @Column(name = "location", length = 64)
    private String location;

    @Column(name = "role", nullable = false, length = 16)
    private String role;
    
    // insertable과 updatable 속성을 false로 설정하여, 엔티티가 처음 저장될 때와 업데이트될 때 자동으로 값이 설정되도록 
    // columnDefinition을 통해 DEFAULT CURRENT_TIMESTAMP를 반영
    @Column(name = "createdAt", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updatedAt", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedAt;
}

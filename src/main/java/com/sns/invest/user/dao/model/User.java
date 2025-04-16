package com.sns.invest.user.dao.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 16)
    @Column(name = "username")
    private String username;

    @NotNull
    @Size(max = 128)
    @Column(name = "password")
    private String password;

    @NotNull
    @Size(max = 16)
    @Column(name = "nickName")
    private String nickName;

    @NotNull
    @Size(max = 64)
    @Column(name = "email")
    private String email;

    @Size(max = 128)
    @Column(name = "profileImage")
    private String profileImage;

    @Size(max = 128)
    @Column(name = "profileStatusMessage")
    private String profileStatusMessage;

    @Size(max = 64)
    @Column(name = "location")
    private String location;

    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private Role role;
    
    // insertable과 updatable 속성을 false로 설정하여, 엔티티가 처음 저장될 때와 업데이트될 때 자동으로 값이 설정되도록 
    // columnDefinition을 통해 DEFAULT CURRENT_TIMESTAMP를 반영
    @Column(name = "createdAt", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updatedAt", insertable = false, updatable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
    
    @Builder
	public User(String username, String password, String nickName, String email, String profileImage,
			String profileStatusMessage, String location, Role role) {
		this.username = username;
		this.password = password;
		this.nickName = nickName;
		this.email = email;
		this.profileImage = profileImage;
		this.profileStatusMessage = profileStatusMessage;
		this.location = location;
		this.role = role;
	}

	public void updateProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public void updateProfileStatusMessage(String profileStatusMessage) {
		this.profileStatusMessage = profileStatusMessage;
	}
	
	public void updateLocation(String location) {
		this.location = location;
	}
    
    public void updatePassword(String password) {
    	this.password = password;
    }
    
}

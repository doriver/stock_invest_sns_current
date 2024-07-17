package com.sns.invest.post.model.local;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "local_post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LocalJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "userId", nullable = false)
    private int userId;

    @Column(name = "userNickName", nullable = false, length = 16)
    private String userNickName;

    @Column(name = "userLocation", length = 64)
    private String userLocation;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "imagePath", length = 128)
    private String imagePath;

    @Column(name = "createdAt", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updatedAt", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @Builder
	public LocalJpa(int userId, String userNickName, String userLocation, String content, String imagePath) {
		this.userId = userId;
		this.userNickName = userNickName;
		this.userLocation = userLocation;
		this.content = content;
		this.imagePath = imagePath;
	}
    
    
}

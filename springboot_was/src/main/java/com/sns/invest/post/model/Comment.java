package com.sns.invest.post.model;

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
@Table(name = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type", nullable = false, length = 16)
    private String type;

    @Column(name = "postId", nullable = false)
    private int postId;

    @Column(name = "userId", nullable = false)
    private int userId;

    @Column(name = "userNickName", nullable = false, length = 16)
    private String userNickName;

    @Column(name = "content", length = 256)
    private String content;

    @Column(name = "createdAt", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updatedAt", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @Builder
	public Comment(String type, int postId, int userId, String userNickName, String content) {
		this.type = type;
		this.postId = postId;
		this.userId = userId;
		this.userNickName = userNickName;
		this.content = content;
	}
    
    
}

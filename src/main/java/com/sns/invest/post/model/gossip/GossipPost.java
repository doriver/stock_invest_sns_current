package com.sns.invest.post.model.gossip;

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
@Table(name = "gossip_post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GossipPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "userId", nullable = false)
    private int userId;

    @Column(name = "userNickName", nullable = false, length = 16)
    private String userNickName;

    @Column(name = "corporation", nullable = false, length = 32)
    private String corporation;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "createdAt", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updatedAt", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @Builder
	public GossipPost(int userId, String userNickName, String corporation, String content) {
		this.userId = userId;
		this.userNickName = userNickName;
		this.corporation = corporation;
		this.content = content;
	}
    
    
}

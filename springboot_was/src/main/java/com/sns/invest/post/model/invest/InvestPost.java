package com.sns.invest.post.model.invest;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sns.invest.user.model.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invest_post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InvestPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "userId", nullable = false)
    private int userId;

    @Column(name = "userNickName", nullable = false, length = 16)
    private String userNickName;

    @Column(name = "investStyle", nullable = false, length = 16)
    private String investStyle;

    @Column(name = "stockItemName", nullable = false, length = 32)
    private String stockItemName;

    @Column(name = "investmentOpinion", length = 16)
    private String investmentOpinion;

    @Column(name = "investmentProcess", nullable = false, length = 16)
    private String investmentProcess;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "imagePath", length = 128)
    private String imagePath;

    @Column(name = "createdAt", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updatedAt", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @Builder
	public InvestPost(int userId, String userNickName, String investStyle, String stockItemName,
			String investmentOpinion, String investmentProcess, String content, String imagePath) {
		this.userId = userId;
		this.userNickName = userNickName;
		this.investStyle = investStyle;
		this.stockItemName = stockItemName;
		this.investmentOpinion = investmentOpinion;
		this.investmentProcess = investmentProcess;
		this.content = content;
		this.imagePath = imagePath;
	}
    
    
}

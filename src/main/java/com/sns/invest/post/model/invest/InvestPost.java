package com.sns.invest.post.model.invest;

import java.util.Date;

public class InvestPost {
	private int id;
	private int userId;
	private String userNickName;
	private String investStyle;
	private String stockItemName;
	private String investmentOpinion;
	private String investmentProcess;
	private String content;
	private String imagePath;
	private Date createdAt;
	private Date updatedAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	public String getInvestStyle() {
		return investStyle;
	}
	public void setInvestStyle(String investStyle) {
		this.investStyle = investStyle;
	}
	public String getStockItemName() {
		return stockItemName;
	}
	public void setStockItemName(String stockItemName) {
		this.stockItemName = stockItemName;
	}
	public String getInvestmentOpinion() {
		return investmentOpinion;
	}
	public void setInvestmentOpinion(String investmentOpinion) {
		this.investmentOpinion = investmentOpinion;
	}
	public String getInvestmentProcess() {
		return investmentProcess;
	}
	public void setInvestmentProcess(String investmentProcess) {
		this.investmentProcess = investmentProcess;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}

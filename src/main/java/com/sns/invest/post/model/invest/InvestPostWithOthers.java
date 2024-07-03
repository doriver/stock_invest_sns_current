package com.sns.invest.post.model.invest;

import java.util.List;

import com.sns.invest.post.model.CommentJpa;

public class InvestPostWithOthers {
	
	private InvestJpa investPost;
	private List<CommentJpa> commentList;
	private boolean isLike;
	private int likeCount;
	private String writerProfileImage;
	
	public String getWriterProfileImage() {
		return writerProfileImage;
	}
	public void setWriterProfileImage(String writerProfileImage) {
		this.writerProfileImage = writerProfileImage;
	}
	public InvestJpa getInvestPost() {
		return investPost;
	}
	public void setInvestPost(InvestJpa investPost) {
		this.investPost = investPost;
	}
	public List<CommentJpa> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentJpa> commentList) {
		this.commentList = commentList;
	}
	public boolean isLike() {
		return isLike;
	}
	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

}

package com.sns.invest.post.model.invest;

import java.util.List;

import com.sns.invest.post.model.Comment;

public class InvestPostWithOthers {
	
	private InvestPost investPost;
	private List<Comment> commentList;
	private boolean isLike;
	private int likeCount;
	private String writerProfileImage;
	
	public String getWriterProfileImage() {
		return writerProfileImage;
	}
	public void setWriterProfileImage(String writerProfileImage) {
		this.writerProfileImage = writerProfileImage;
	}
	public InvestPost getInvestPost() {
		return investPost;
	}
	public void setInvestPost(InvestPost investPost) {
		this.investPost = investPost;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
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

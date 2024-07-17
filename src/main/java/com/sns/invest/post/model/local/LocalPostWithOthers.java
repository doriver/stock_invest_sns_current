package com.sns.invest.post.model.local;

import java.util.List;

import com.sns.invest.post.model.CommentJpa;

public class LocalPostWithOthers {
	private LocalJpa localPost;
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
	public LocalJpa getLocalPost() {
		return localPost;
	}
	public void setLocalPost(LocalJpa localPost) {
		this.localPost = localPost;
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

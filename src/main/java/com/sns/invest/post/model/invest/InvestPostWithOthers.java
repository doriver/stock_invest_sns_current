package com.sns.invest.post.model.invest;

import java.util.List;

import com.sns.invest.post.model.Comment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class InvestPostWithOthers {
	
	private InvestPost investPost;
	private List<Comment> commentList;
	private boolean isLike;
	private int likeCount;
	private String writerProfileImage;
	
	@Builder
	public InvestPostWithOthers(InvestPost investPost, List<Comment> commentList, boolean isLike, int likeCount,
			String writerProfileImage) {
		this.investPost = investPost;
		this.commentList = commentList;
		this.isLike = isLike;
		this.likeCount = likeCount;
		this.writerProfileImage = writerProfileImage;
	}
	
	

}

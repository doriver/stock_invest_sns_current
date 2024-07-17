package com.sns.invest.post.model.invest;

import java.util.List;

import com.sns.invest.post.model.Comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvestPostWithOthers {
	
	private InvestPost investPost;
	private List<Comment> commentList;
	private boolean isLike;
	private int likeCount;
	private String writerProfileImage;

}

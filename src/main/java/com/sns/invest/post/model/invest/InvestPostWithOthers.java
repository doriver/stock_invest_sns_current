package com.sns.invest.post.model.invest;

import java.util.List;

import com.sns.invest.post.model.CommentJpa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvestPostWithOthers {
	
	private InvestJpa investPost;
	private List<CommentJpa> commentList;
	private boolean isLike;
	private int likeCount;
	private String writerProfileImage;

}

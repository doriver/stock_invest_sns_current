package com.sns.invest.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.invest.post.model.Comment;
import com.sns.invest.post.model.gossip.GossipPost;
import com.sns.invest.post.model.gossip.GossipPostWithOthers;
import com.sns.invest.post.model.invest.InvestPost;
import com.sns.invest.post.model.invest.InvestPostWithOthers;
import com.sns.invest.post.model.local.LocalPost;
import com.sns.invest.post.model.local.LocalPostWithOthers;
import com.sns.invest.comment.bo.CommentBO;
import com.sns.invest.common.FileManagerService;
import com.sns.invest.post.dao.PostDAO;


@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;

	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	public int addPost(int userId, String userNickName, String content, MultipartFile file
			, String investStyle, String stockItemName, String investmentOpinion, String investmentProcess) {
		
		FileManagerService fileManager = new FileManagerService();
		
		String filePath = fileManager.saveFile(userId, file);
		
		if(filePath == null) {
			return -1;
		}
		
		return postDAO.insertInvestPost(userId, userNickName, content, filePath, investStyle, stockItemName, investmentOpinion, investmentProcess);
	}
	
	public List<InvestPostWithOthers> getInvestPostList(int userId) {
		List<InvestPost> postList = postDAO.selectInvestPostList();
		
		List<InvestPostWithOthers> postWithOthersList = new ArrayList<>();
		
		String type = "invest";
		for(InvestPost post:postList) {
			List<Comment> commentList = commentBO.getCommentListByPostIdType(post.getId(),type);
			
			boolean isLike = likeBO.existLike(post.getId(), userId, type);
			int likeCount = likeBO.countLike(post.getId(), type);
			
			InvestPostWithOthers postWithOthers = new InvestPostWithOthers();
			postWithOthers.setInvestPost(post);
			postWithOthers.setCommentList(commentList);
			postWithOthers.setLike(isLike);
			postWithOthers.setLikeCount(likeCount);
			
			postWithOthersList.add(postWithOthers);
		}
		
		return postWithOthersList;
	}

	public List<InvestPostWithOthers> getInvestPostListByUserId(int userId) {
		List<InvestPost> postList = postDAO.selectInvestPostListByUserId(userId);
		
		List<InvestPostWithOthers> postWithOthersList = new ArrayList<>();
		
		String type = "invest";
		for(InvestPost post:postList) {
			List<Comment> commentList = commentBO.getCommentListByPostIdType(post.getId(),type);
			
			boolean isLike = likeBO.existLike(post.getId(), userId, type);
			int likeCount = likeBO.countLike(post.getId(), type);
			
			InvestPostWithOthers postWithOthers = new InvestPostWithOthers();
			postWithOthers.setInvestPost(post);
			postWithOthers.setCommentList(commentList);
			postWithOthers.setLike(isLike);
			postWithOthers.setLikeCount(likeCount);
			
			postWithOthersList.add(postWithOthers);
		}
		
		return postWithOthersList;
	}

	
	public List<GossipPostWithOthers> getGossipPostList(int userId, String corporation) {
		
		//List<GossipPost> postList = new ArrayList<>();
		List<GossipPost> postList = null;
		
		if (corporation == null) {
			postList = postDAO.selectGossipPostList();
		} else {
			postList = postDAO.selectGossipPostListByCorporation(corporation);
		}
		
		List<GossipPostWithOthers> postWithOthersList = new ArrayList<>();
		
		String type = "gossip";
		for(GossipPost post:postList) {
			List<Comment> commentList = commentBO.getCommentListByPostIdType(post.getId(),type);
			
			boolean isLike = likeBO.existLike(post.getId(), userId, type);
			int likeCount = likeBO.countLike(post.getId(), type);
			
			GossipPostWithOthers postWithOthers = new GossipPostWithOthers();
			postWithOthers.setGossipPost(post);
			postWithOthers.setCommentList(commentList);
			postWithOthers.setLike(isLike);
			postWithOthers.setLikeCount(likeCount);
			
			postWithOthersList.add(postWithOthers);
		}
		
		return postWithOthersList;
	}
	
	public int addGossipPost(int userId, String userNickName, String corporation, String content) {
		return postDAO.insertGossipPost(userId, userNickName, corporation, content);
	}

	public List<LocalPostWithOthers> getLocalPostList(int userId, String userLocation) {
		List<LocalPost> postList = postDAO.selectLocalPostList(userLocation);
		
		List<LocalPostWithOthers> postWithOthersList = new ArrayList<>();
		
		String type = "local";
		for(LocalPost post:postList) {
			List<Comment> commentList = commentBO.getCommentListByPostIdType(post.getId(),type);
			
			boolean isLike = likeBO.existLike(post.getId(), userId, type);
			int likeCount = likeBO.countLike(post.getId(), type);
			
			LocalPostWithOthers postWithOthers = new LocalPostWithOthers();
			postWithOthers.setLocalPost(post);
			postWithOthers.setCommentList(commentList);
			postWithOthers.setLike(isLike);
			postWithOthers.setLikeCount(likeCount);
			
			postWithOthersList.add(postWithOthers);
		}
		
		return postWithOthersList;
	}

	public int addLocalPost(int userId, String userNickName, String userLocation,
			String content, MultipartFile file) {
		
		FileManagerService fileManager = new FileManagerService();
		
		String filePath = fileManager.saveFile(userId, file);
		
		if(filePath == null) {
			return -1;
		}
		
		return postDAO.insertLocalPost(userId, userNickName, userLocation, content, filePath);
	}

}

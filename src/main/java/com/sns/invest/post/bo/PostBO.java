package com.sns.invest.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sns.invest.post.model.Comment;
import com.sns.invest.post.model.CommentJpa;
import com.sns.invest.post.model.gossip.GossipJpa;
import com.sns.invest.post.model.gossip.GossipPost;
import com.sns.invest.post.model.gossip.GossipPostWithOthers;
import com.sns.invest.post.model.invest.InvestJpa;
import com.sns.invest.post.model.invest.InvestPost;
import com.sns.invest.post.model.invest.InvestPostWithOthers;
import com.sns.invest.post.model.local.LocalJpa;
import com.sns.invest.post.model.local.LocalPost;
import com.sns.invest.post.model.local.LocalPostWithOthers;
import com.sns.invest.user.bo.UserBO;
import com.sns.invest.user.dao.UserRepository;

import lombok.RequiredArgsConstructor;

import com.sns.invest.comment.bo.CommentBO;
import com.sns.invest.common.FileManagerService;
import com.sns.invest.post.dao.GossipPostRepository;
import com.sns.invest.post.dao.InvestPostRepository;
import com.sns.invest.post.dao.LocalPostRepository;
import com.sns.invest.post.dao.PostDAO;
import com.sns.invest.post.dao.custom.InvestPostRepositoryCustom;


@Service
@RequiredArgsConstructor // final이 붙은 필드를 모아서 생성자를 만들어줌
public class PostBO {
	
	private final InvestPostRepository investPostRepository;
	private final GossipPostRepository gossipPostRepository;
	private final LocalPostRepository localPostRepository;
		
	@Autowired
	private PostDAO postDAO;

	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private UserBO userBO;
	
	public int addPost(int userId, String userNickName, String content, MultipartFile file
			, String investStyle, String stockItemName, String investmentOpinion, String investmentProcess) {
		
		String filePath = null;
		
		if(file != null) {
			FileManagerService fileManager = new FileManagerService();
			
			filePath = fileManager.saveFile(userId, file);
			
			if(filePath == null) {
				return -1;
			}	
		}
//		FileManagerService fileManager = new FileManagerService();
//		
//		String filePath = fileManager.saveFile(userId, file);
//		
//		if(filePath == null) {
//			return -1;
//		}
		
		return postDAO.insertInvestPost(userId, userNickName, content, filePath, investStyle, stockItemName, investmentOpinion, investmentProcess);
	}
	
	public List<InvestPostWithOthers> getInvestPostList(int myUserId) {
		List<InvestJpa> postList = investPostRepository.findAllByOrderByIdDesc();
		
		List<InvestPostWithOthers> postWithOthersList = new ArrayList<>();
		
		String type = "invest";
		for(InvestJpa post:postList) {
			List<CommentJpa> commentList = commentBO.getCommentListByPostIdType(post.getId(),type);
			
			boolean isLike = likeBO.existLike(post.getId(), myUserId, type);
			int likeCount = likeBO.countLike(post.getId(), type);
			
			String writerProfileImage = userBO.getProfileImage(post.getUserId());
			
			InvestPostWithOthers postWithOthers = new InvestPostWithOthers();
			postWithOthers.setInvestPost(post);
			postWithOthers.setCommentList(commentList);
			postWithOthers.setLike(isLike);
			postWithOthers.setLikeCount(likeCount);
			postWithOthers.setWriterProfileImage(writerProfileImage);
			
			postWithOthersList.add(postWithOthers);
		}
		
		return postWithOthersList;
	}

	public List<InvestPostWithOthers> getInvestPostListByUserId(int userId) {
		List<InvestJpa> postList = investPostRepository.findAllByUserIdOrderByIdDesc(userId);
		
		List<InvestPostWithOthers> postWithOthersList = new ArrayList<>();
		
		String type = "invest";
		for(InvestJpa post:postList) {
			List<CommentJpa> commentList = commentBO.getCommentListByPostIdType(post.getId(),type);
			
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

	public List<InvestPostWithOthers> getFilteredInvestPostList(int myUserId
			, String investStyleForFiltering, String stockItemNameForFiltering
			, String investmentOpinionForFiltering, String investmentProcessForFiltering) {
		

		List<InvestJpa> postList = investPostRepository.findInvestPostsByFilters(
				investStyleForFiltering, stockItemNameForFiltering, investmentOpinionForFiltering, investmentProcessForFiltering
			);

		List<InvestPostWithOthers> postWithOthersList = new ArrayList<>();
		
		String type = "invest";
		for(InvestJpa post:postList) {
			List<CommentJpa> commentList = commentBO.getCommentListByPostIdType(post.getId(),type);
			
			boolean isLike = likeBO.existLike(post.getId(), myUserId, type);
			int likeCount = likeBO.countLike(post.getId(), type);
			
			String writerProfileImage = userBO.getProfileImage(post.getUserId());
		
			InvestPostWithOthers postWithOthers = new InvestPostWithOthers();
			postWithOthers.setInvestPost(post);
			postWithOthers.setCommentList(commentList);
			postWithOthers.setLike(isLike);
			postWithOthers.setLikeCount(likeCount);
			postWithOthers.setWriterProfileImage(writerProfileImage);
			
			postWithOthersList.add(postWithOthers);
		}
		
		return postWithOthersList;
	}

	
	public List<GossipPostWithOthers> getGossipPostList(int myUserId, String corporation) {
		
		List<GossipJpa> postList = null;
		
		if (corporation == null) {
			postList = gossipPostRepository.findAllByOrderByIdDesc();
		} else {
			postList = gossipPostRepository.findAllByCorporationOrderByIdDesc(corporation);
		}
	
		List<GossipPostWithOthers> postWithOthersList = new ArrayList<>();
		
		String type = "gossip";
		for(GossipJpa post:postList) {
			List<CommentJpa> commentList = commentBO.getCommentListByPostIdType(post.getId(),type);
			
			boolean isLike = likeBO.existLike(post.getId(), myUserId, type);
			int likeCount = likeBO.countLike(post.getId(), type);
			
			String writerProfileImage = userBO.getProfileImage(post.getUserId());
			
			GossipPostWithOthers postWithOthers = new GossipPostWithOthers();
			postWithOthers.setGossipPost(post);
			postWithOthers.setCommentList(commentList);
			postWithOthers.setLike(isLike);
			postWithOthers.setLikeCount(likeCount);
			postWithOthers.setWriterProfileImage(writerProfileImage);

			postWithOthersList.add(postWithOthers);
		}
		
		return postWithOthersList;
	}
	
	public int addGossipPost(int userId, String userNickName, String corporation, String content) {
		return postDAO.insertGossipPost(userId, userNickName, corporation, content);
	}

	public List<LocalPostWithOthers> getLocalPostList(int myUserId, String userLocation) {
		List<LocalJpa> postList = localPostRepository.findAllByUserLocationOrderByIdDesc(userLocation);
		
		List<LocalPostWithOthers> postWithOthersList = new ArrayList<>();
		
		String type = "local";
		for(LocalJpa post:postList) {
			List<CommentJpa> commentList = commentBO.getCommentListByPostIdType(post.getId(),type);
			
			boolean isLike = likeBO.existLike(post.getId(), myUserId, type);
			int likeCount = likeBO.countLike(post.getId(), type);
			
			String writerProfileImage = userBO.getProfileImage(post.getUserId());

			
			LocalPostWithOthers postWithOthers = new LocalPostWithOthers();
			postWithOthers.setLocalPost(post);
			postWithOthers.setCommentList(commentList);
			postWithOthers.setLike(isLike);
			postWithOthers.setLikeCount(likeCount);
			postWithOthers.setWriterProfileImage(writerProfileImage);
			
			postWithOthersList.add(postWithOthers);
		}
		
		return postWithOthersList;
	}

	public int addLocalPost(int myUserId, String userNickName,
			String content, MultipartFile file) {
		
		String filePath = null;
		
		if(file != null) {
			FileManagerService fileManager = new FileManagerService();
			
			filePath = fileManager.saveFile(myUserId, file);
			
			if(filePath == null) {
				return -1;
			}	
		}
//		FileManagerService fileManager = new FileManagerService();
//		
//		String filePath = fileManager.saveFile(userId, file);
//		
//		if(filePath == null) {
//			return -1;
//		}
		
		String myLocation = userBO.getlocation(myUserId);
		
		return postDAO.insertLocalPost(myUserId, userNickName, myLocation, content, filePath);
	}
	
    @Transactional // 이 메소드가 트랜잭션 내에서 실행되어야 함을 나타냄
	public boolean deletePost(int postId, int userId, String type) {
		
		String imagePath = null;
		int deleteCount = 0;
		
		if (type.equals("invest")) {		
			imagePath = postDAO.selectInvestPostImagePath(postId);
			deleteCount = postDAO.deleteInvestPost(postId, userId);	
		} 
			
		if (type.equals("local")) {
			imagePath = postDAO.selectLocalPostImagePath(postId);
			deleteCount = postDAO.deleteLocalPost(postId, userId);
		}

		if (type.equals("gossip")) {
			deleteCount = postDAO.deleteGossipPost(postId, userId);	
		}
		
		if(deleteCount != 1) {
			return false;
		}
		
		if(imagePath != null) {
			FileManagerService fileManagerService = new FileManagerService();
			fileManagerService.removeFile(imagePath);			
		}

		commentBO.deleteComment(postId, type);
		likeBO.deleteLikeInPost(postId, type);
		return true;
	}

}

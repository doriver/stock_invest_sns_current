package com.sns.invest.post.bo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sns.invest.post.model.Comment;
import com.sns.invest.post.model.gossip.GossipPost;
import com.sns.invest.post.model.gossip.GossipPostWithOthers;
import com.sns.invest.post.model.invest.InvestPost;
import com.sns.invest.post.model.invest.InvestPostWithOthers;
import com.sns.invest.post.model.local.LocalPost;
import com.sns.invest.post.model.local.LocalPostWithOthers;
import com.sns.invest.user.bo.UserBO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.sns.invest.comment.bo.CommentBO;
import com.sns.invest.comment.dao.CommentRepository;
import com.sns.invest.common.FileManagerService;
import com.sns.invest.post.dao.GossipPostRepository;
import com.sns.invest.post.dao.InvestPostRepository;
import com.sns.invest.post.dao.LocalPostRepository;

@Slf4j
@Service
@RequiredArgsConstructor // final이 붙은 필드를 모아서 생성자를 만들어줌
public class PostBO {
	
	private final InvestPostRepository investPostRepository;
	private final GossipPostRepository gossipPostRepository;
	private final LocalPostRepository localPostRepository;
	private final CommentRepository commentRepository;
		
	private final CommentBO commentBO;	
	private final LikeBO likeBO;
	private final UserBO userBO;
	
	@Transactional
	public int addPost(int userId, String userNickName, String content, MultipartFile file
			, String investStyle, String stockItemName, String investmentOpinion, String investmentProcess) {
		
		String filePath = null;
		
		if(file != null) {
			FileManagerService fileManager = new FileManagerService();
			filePath = fileManager.saveFile(userId, file);
		
			if(filePath == null) {
				log.error("saveFile() 실패");
				return -1;
			}	
		}
		
		int result = -1;
		
		InvestPost post = InvestPost.builder()
							.userId(userId).userNickName(userNickName).content(content).imagePath(filePath)
							.investStyle(investStyle).stockItemName(stockItemName).investmentOpinion(investmentOpinion).investmentProcess(investmentProcess)
							.build();
		
		try {
			if ( investPostRepository.save(post) instanceof InvestPost ) {
				result = 1;
			}
		} catch (Exception e) {
			log.error("[PostBO addPost] save()실패");
	        throw e;
		}
		
		return result;
	}
	
	public List<InvestPostWithOthers> getInvestPostList(int myUserId) {
		List<InvestPost> postList = investPostRepository.findAllByOrderByIdDesc();
		
		List<InvestPostWithOthers> postWithOthersList = new LinkedList<>();
		
		String type = "invest";
		for(InvestPost post:postList) {
			List<Comment> commentList = commentBO.getCommentListByPostIdType(post.getId(),type);
			
			boolean isLike = likeBO.existLike(post.getId(), myUserId, type);
			int likeCount = likeBO.countLike(post.getId(), type);
			
			String writerProfileImage = userBO.getProfileImage(post.getUserId());
			
			InvestPostWithOthers postWithOthers 
				= InvestPostWithOthers.builder()
							.investPost(post).writerProfileImage(writerProfileImage)
							.commentList(commentList).isLike(isLike).likeCount(likeCount)
							.build();
				
			postWithOthersList.add(postWithOthers);
		}
		
		return postWithOthersList;
	}

	public List<InvestPostWithOthers> getInvestPostListByUserId(int userId) {
		List<InvestPost> postList = investPostRepository.findAllByUserIdOrderByIdDesc(userId);
		
		List<InvestPostWithOthers> postWithOthersList = new ArrayList<>();
		
		String type = "invest";
		for(InvestPost post:postList) {
			List<Comment> commentList = commentBO.getCommentListByPostIdType(post.getId(),type);
			
			boolean isLike = likeBO.existLike(post.getId(), userId, type);
			int likeCount = likeBO.countLike(post.getId(), type);
			
			InvestPostWithOthers postWithOthers 
				= InvestPostWithOthers.builder()
							.investPost(post)
							.commentList(commentList).isLike(isLike).likeCount(likeCount)
							.build();
			
			postWithOthersList.add(postWithOthers);
		}
		
		return postWithOthersList;
	}

	public List<InvestPostWithOthers> getFilteredInvestPostList(int myUserId
			, String investStyleForFiltering, String stockItemNameForFiltering
			, String investmentOpinionForFiltering, String investmentProcessForFiltering) {
		

		List<InvestPost> postList = investPostRepository.findInvestPostsByFilters(
				investStyleForFiltering, stockItemNameForFiltering, investmentOpinionForFiltering, investmentProcessForFiltering
			);

		List<InvestPostWithOthers> postWithOthersList = new ArrayList<>();
		
		String type = "invest";
		for(InvestPost post:postList) {
			List<Comment> commentList = commentBO.getCommentListByPostIdType(post.getId(),type);
			
			boolean isLike = likeBO.existLike(post.getId(), myUserId, type);
			int likeCount = likeBO.countLike(post.getId(), type);
			
			String writerProfileImage = userBO.getProfileImage(post.getUserId());
			
			InvestPostWithOthers postWithOthers 
				= InvestPostWithOthers.builder()
							.investPost(post).writerProfileImage(writerProfileImage)
							.commentList(commentList).isLike(isLike).likeCount(likeCount)
							.build();
			
			postWithOthersList.add(postWithOthers);
		}
		
		return postWithOthersList;
	}

	
	public List<GossipPostWithOthers> getGossipPostList(int myUserId, String corporation) {
		
		List<GossipPost> postList = null;
		
		if (corporation == null) {
			postList = gossipPostRepository.findAllByOrderByIdDesc();
		} else {
			postList = gossipPostRepository.findAllByCorporationOrderByIdDesc(corporation);
		}
	
		List<GossipPostWithOthers> postWithOthersList = new ArrayList<>();
		
		String type = "gossip";
		for(GossipPost post:postList) {
			List<Comment> commentList = commentBO.getCommentListByPostIdType(post.getId(),type);
			
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
	
	@Transactional
	public int addGossipPost(int userId, String userNickName, String corporation, String content) {
		
		int result = -1;
		
		GossipPost post = GossipPost.builder()
							.userId(userId).userNickName(userNickName)
							.corporation(corporation).content(content)
							.build();
	
		try {
			if ( gossipPostRepository.save(post) instanceof GossipPost ) {
				result = 1;
			}
		} catch (Exception e) {
			log.error("[PostBO addGossipPost] save()실패");
	        throw e;
		}
		
		return result;
	}

	public List<LocalPostWithOthers> getLocalPostList(int myUserId, String userLocation) {
		List<LocalPost> postList = localPostRepository.findAllByUserLocationOrderByIdDesc(userLocation);
		
		List<LocalPostWithOthers> postWithOthersList = new ArrayList<>();
		
		String type = "local";
		for(LocalPost post:postList) {
			List<Comment> commentList = commentBO.getCommentListByPostIdType(post.getId(),type);
			
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

	@Transactional
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

		String myLocation = userBO.getlocation(myUserId);
		
		LocalPost post = LocalPost.builder()
							.userId(myUserId).userNickName(userNickName)
							.userLocation(myLocation).content(content).imagePath(filePath)
							.build();
		
		int result = -1;
		
		try {
			if ( localPostRepository.save(post) instanceof LocalPost ) {
				result = 1;
			}
		} catch (Exception e) {
			log.error("[PostBO addLocalPost] save()실패");
	        throw e;
		}					
		
		return result;
	}
	
    @Transactional // 이 메소드가 트랜잭션 내에서 실행되어야 함을 나타냄
	public boolean deletePost(int postId, int userId, String type) {
		
		String imagePath = null;
		int deleteCount = 0;
		
		if (type.equals("invest")) {		
			imagePath = investPostRepository.findImagePathById(postId);
			investPostRepository.deleteByIdAndUserId(postId, userId);
		} 
			
		if (type.equals("local")) {
			imagePath = localPostRepository.findImagePathById(postId);
			localPostRepository.deleteByIdAndUserId(postId, userId);
		}

		if (type.equals("gossip")) {
			gossipPostRepository.deleteByIdAndUserId(postId, userId);
		}
		
		if(imagePath != null) {
			FileManagerService fileManagerService = new FileManagerService();
			fileManagerService.removeFile(imagePath);			
		}

		commentRepository.deleteByPostIdAndType(postId, type);
		likeBO.deleteLikeInPost(postId, type);
		return true;
	}

}

package com.sns.invest.post.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sns.invest.comment.dao.CommentRepository;
import com.sns.invest.post.dao.LikeDAO;
import com.sns.invest.post.dao.LikeRepository;
import com.sns.invest.post.model.Like;
import com.sns.invest.user.model.UserJpa;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // final이 붙은 필드를 모아서 생성자를 만들어줌
public class LikeBO {
	
	private final LikeRepository likeRepository;
	
	//사용자기준 좋아요 여부 체크 , 좋아요 되있으면 true, 안되있으면 false
	public boolean existLike(int postId, int userId, String type) {
		Long count = likeRepository.countByPostIdAndUserIdAndType(postId, userId, type);
		
		if(count >= 1) {
			return true;
		} else {
			return false;
		}
	}
	
	// 좋아요 되고 true, 좋아요 취소되고 false
	@Transactional // 이 메소드가 트랜잭션 내에서 실행되어야 함을 나타냄
	public boolean like(int postId, int userId, String type) {
		
		// 만약 해당 포스트에 좋아요가 되어 있다면 좋아요 취소하고 , false
		if(this.existLike(postId, userId, type)) {
			likeRepository.deleteByTypeAndPostIdAndUserId(type, postId, userId);
			return false;
		} else  {  // 만약 해당 포스트에 좋아요가 안되어 있다면 좋아요되고 , true
			Like like = new Like();
			like.setType(type);
			like.setPostId(postId);
			like.setUserId(userId);
			likeRepository.save(like);
			return true;
		}
	}
	
	// 좋아요 갯수 
	public int countLike(int postId, String type) {
		Long count = likeRepository.countByPostIdAndType(postId, type);
		return count.intValue();
	}
	
	public void deleteLikeInPost(int postId, String type) {
		likeRepository.deleteByTypeAndPostId(type, postId);
	}

	
}

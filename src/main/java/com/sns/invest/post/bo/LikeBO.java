package com.sns.invest.post.bo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sns.invest.comment.dao.CommentRepository;
import com.sns.invest.post.dao.RedisDAO;
import com.sns.invest.user.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // final이 붙은 필드를 모아서 생성자를 만들어줌
public class LikeBO {
	
	private final RedisDAO redisDAO;
	
	//사용자기준 좋아요 여부, 좋아요 되있으면 true, 안되있으면 false
	public boolean existLike(int postId, int userId, String type) {
        String key = type + ":" + postId;
        return redisDAO.sismember(key, Integer.toString(userId)); // 해당글 좋아요에 사용자id 있는지 여부
	}
	
	// 좋아요 되고 true, 좋아요 취소되고 false
	@Transactional // 이 메소드가 트랜잭션 내에서 실행되어야 함을 나타냄
	public boolean like(int postId, int userId, String type) {
        String key = type + ":" + postId;
        
		// 만약 해당 포스트에 좋아요가 되어 있다면 좋아요 취소하고 , false
		if(this.existLike(postId, userId, type)) {
            redisDAO.srem(key, Integer.toString(userId)); // 해당글 좋아요에서 사용자id 삭제
			return false;
		} else  {  // 만약 해당 포스트에 좋아요가 안되어 있다면 좋아요되고 , true
            redisDAO.sadd(key, Integer.toString(userId)); // 해당글 좋아요에 사용자id 추가
			return true;
		}
	}
	
	// 좋아요 갯수 
	public int countLike(int postId, String type) {
        String key = type + ":" + postId;
        return (redisDAO.scard(key)).intValue(); // 해당글 좋아요 원소개수 얻음
	}
	
	public void deleteLikeInPost(int postId, String type) {
        String key = type + ":" + postId;
        redisDAO.del(key); // 해당글에 대응하는 key 삭제
	}

	
}

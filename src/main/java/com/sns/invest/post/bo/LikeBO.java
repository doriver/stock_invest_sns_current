package com.sns.invest.post.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.invest.post.dao.LikeDAO;

@Service
public class LikeBO {
	@Autowired
	private LikeDAO likeDAO;
	
	//사용자기준 좋아요 여부 체크 , 좋아요 되있으면 true, 안되있으면 false
	public boolean existLike(int postId, int userId, String type) {
		int count = likeDAO.selectCountLike(postId, userId, type);
		
		if(count >= 1) {
			return true;
		} else {
			return false;
		}
	}
	
	// 좋아요 되고 true, 좋아요 취소되고 false
	public boolean like(int postId, int userId, String type) {
		
		// 만약 해당 포스트에 좋아요가 되어 있다면 좋아요 취소하고 , false
		if(this.existLike(postId, userId, type)) {
			likeDAO.deleteLike(postId, userId, type);
			return false;
		} else  {  // 만약 해당 포스트에 좋아요가 안되어 있다면 좋아요되고 , true
			likeDAO.insertLike(postId, userId, type);
			return true;
		}
	}
	
	// 좋아요 갯수 
	public int countLike(int postId, String type) {
		return likeDAO.selectCountLikeByPostIdType(postId, type);
	}
	
	public int deleteLikeInPost(int postId, String type) {
		return likeDAO.deleteLikeInPost(postId, type);
	}

	
}

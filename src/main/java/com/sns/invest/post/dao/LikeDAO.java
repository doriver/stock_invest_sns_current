package com.sns.invest.post.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {
	
	public int insertLike(@Param("postId") int postId
			, @Param("userId") int userId
			, @Param("type") String type);
	
	public int selectCountLike(@Param("postId") int postId
			, @Param("userId") int userId
			, @Param("type") String type);

	public int deleteLike(@Param("postId") int postId
			, @Param("userId") int userId
			, @Param("type") String type);
	
	public int selectCountLikeByPostIdType(@Param("postId") int postId
			, @Param("type") String type);
	
//	public int deleteLikeByPostId(@Param("postId") int postId
//			, @Param("type") String type);

}

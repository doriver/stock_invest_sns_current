package com.sns.invest.post.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDAO {

	public int insertInvestPost(
			@Param("userId") int userId
			, @Param("userNickName") String userNickName
			, @Param("content") String content
			, @Param("imagePath") String imagePath
			, @Param("investStyle") String investStyle
			, @Param("stockItemName") String stockItemName
			, @Param("investmentOpinion") String investmentOpinion
			, @Param("investmentProcess") String investmentProcess
			);
	
}

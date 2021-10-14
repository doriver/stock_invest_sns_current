package com.sns.invest.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.invest.post.model.gossip.GossipPost;
import com.sns.invest.post.model.invest.InvestPost;
import com.sns.invest.post.model.local.LocalPost;

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
	
	public List<InvestPost> selectInvestPostList();

	public List<InvestPost> selectInvestPostListByUserId(
			@Param("userId") int userId);

	public List<InvestPost> selectFilteredInvestPostList(
			@Param("investStyleForFiltering") String investStyleForFiltering
			, @Param("stockItemNameForFiltering") String stockItemNameForFiltering
			, @Param("investmentOpinionForFiltering") String investmentOpinionForFiltering
			, @Param("investmentProcessForFiltering") String investmentProcessForFiltering
			);

	public List<GossipPost> selectGossipPostList();

	public List<GossipPost> selectGossipPostListByCorporation(
			@Param("corporation") String corporation);
	
	public int insertGossipPost(
			@Param("userId") int userId
			, @Param("userNickName") String userNickName
			, @Param("corporation") String corporation
			, @Param("content")String content
			);

	public List<LocalPost> selectLocalPostList(
			@Param("userLocation") String userLocation);

	public int insertLocalPost(
			@Param("userId") int myUserId
			, @Param("userNickName") String userNickName
			, @Param("userLocation") String userLocation
			, @Param("content") String content
			, @Param("imagePath") String filePath
			);

}

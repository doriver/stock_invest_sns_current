package com.sns.invest.post.dao.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sns.invest.post.model.invest.InvestPost;
import com.sns.invest.post.model.invest.QInvestPost;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class InvestPostRepositoryCustomImpl implements InvestPostRepositoryCustom{

	private final JPAQueryFactory queryFactory;
	
	@Override
	public List<InvestPost> findInvestPostsByFilters(
			String investStyleForFiltering, String stockItemNameForFiltering,
			String investmentOpinionForFiltering, String investmentProcessForFiltering) {
		
		BooleanBuilder builder = new BooleanBuilder();
		QInvestPost qInvestPost = QInvestPost.investPost;
		
		if (investStyleForFiltering != "") {
			builder.and(qInvestPost.investStyle.eq(investStyleForFiltering));
			
			if (stockItemNameForFiltering != "") {
				builder.and(qInvestPost.stockItemName.eq(stockItemNameForFiltering));
			}
			if (investmentOpinionForFiltering != "") {
				builder.and(qInvestPost.investmentOpinion.eq(investmentOpinionForFiltering));
			}
			if (investmentProcessForFiltering != "") {
				builder.and(qInvestPost.investmentProcess.eq(investmentProcessForFiltering));
			}
		} else if (stockItemNameForFiltering != "") {
			builder.and(qInvestPost.stockItemName.eq(stockItemNameForFiltering));
			
			if (investmentOpinionForFiltering != "") {
				builder.and(qInvestPost.investmentOpinion.eq(investmentOpinionForFiltering));
			}
			if (investmentProcessForFiltering != "") {
				builder.and(qInvestPost.investmentProcess.eq(investmentProcessForFiltering));
			}
		} else if (investmentOpinionForFiltering != "") {
			builder.and(qInvestPost.investmentOpinion.eq(investmentOpinionForFiltering));
			
			if (investmentProcessForFiltering != "") {
				builder.and(qInvestPost.investmentProcess.eq(investmentProcessForFiltering));
			}
		} else if (investmentProcessForFiltering != "") {
			builder.and(qInvestPost.investmentProcess.eq(investmentProcessForFiltering));
		}

		 return queryFactory.selectFrom(qInvestPost)
	                .where(builder)
	                .orderBy(qInvestPost.id.desc())
	                .fetch();
	}

}

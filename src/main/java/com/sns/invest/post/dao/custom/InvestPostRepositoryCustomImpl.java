package com.sns.invest.post.dao.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sns.invest.post.model.invest.InvestJpa;
import com.sns.invest.post.model.invest.QInvestJpa;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class InvestPostRepositoryCustomImpl implements InvestPostRepositoryCustom{

	private final JPAQueryFactory queryFactory;
	
	@Override
	public List<InvestJpa> findInvestPostsByFilters(
			String investStyleForFiltering, String stockItemNameForFiltering,
			String investmentOpinionForFiltering, String investmentProcessForFiltering) {
		
		BooleanBuilder builder = new BooleanBuilder();
		QInvestJpa qInvestJpa = QInvestJpa.investJpa;
		
		if (investStyleForFiltering != "") {
			builder.and(qInvestJpa.investStyle.eq(investStyleForFiltering));
			
			if (stockItemNameForFiltering != "") {
				builder.and(qInvestJpa.stockItemName.eq(stockItemNameForFiltering));
			}
			if (investmentOpinionForFiltering != "") {
				builder.and(qInvestJpa.investmentOpinion.eq(investmentOpinionForFiltering));
			}
			if (investmentProcessForFiltering != "") {
				builder.and(qInvestJpa.investmentProcess.eq(investmentProcessForFiltering));
			}
		} else if (stockItemNameForFiltering != "") {
			builder.and(qInvestJpa.stockItemName.eq(stockItemNameForFiltering));
			
			if (investmentOpinionForFiltering != "") {
				builder.and(qInvestJpa.investmentOpinion.eq(investmentOpinionForFiltering));
			}
			if (investmentProcessForFiltering != "") {
				builder.and(qInvestJpa.investmentProcess.eq(investmentProcessForFiltering));
			}
		} else if (investmentOpinionForFiltering != "") {
			builder.and(qInvestJpa.investmentOpinion.eq(investmentOpinionForFiltering));
			
			if (investmentProcessForFiltering != "") {
				builder.and(qInvestJpa.investmentProcess.eq(investmentProcessForFiltering));
			}
		} else if (investmentProcessForFiltering != "") {
			builder.and(qInvestJpa.investmentProcess.eq(investmentProcessForFiltering));
		}

		 return queryFactory.selectFrom(qInvestJpa)
	                .where(builder)
	                .orderBy(qInvestJpa.id.desc())
	                .fetch();
	}

}

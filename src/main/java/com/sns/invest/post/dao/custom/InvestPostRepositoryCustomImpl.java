package com.sns.invest.post.dao.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sns.invest.post.model.invest.InvestJpa;

import lombok.RequiredArgsConstructor;

//QInvestJpa import
//import static com.sns.invest.post.model.invest.QInvestJpa.investJpa;

@Repository
@RequiredArgsConstructor
public class InvestPostRepositoryCustomImpl implements InvestPostRepositoryCustom{

	private final JPAQueryFactory queryFactory;
	
	@Override
	public List<InvestJpa> findInvestPostsByFilters(
			String investStyleForFiltering, String stockItemNameForFiltering,
			String investmentOpinionForFiltering, String investmentProcessForFiltering) {
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if (investStyleForFiltering != "") {
//			builder.and(investJpa.investStyle.eq(investStyleForFiltering));
		} else if (stockItemNameForFiltering != "") {
			
		} else if (investmentOpinionForFiltering != "") {
			
		} else if (investmentProcessForFiltering != "") {
			
		}

		return null;
	}

}

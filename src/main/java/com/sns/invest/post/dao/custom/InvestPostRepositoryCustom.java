package com.sns.invest.post.dao.custom;

import java.util.List;

import com.sns.invest.post.model.invest.InvestJpa;

public interface InvestPostRepositoryCustom {
	 List<InvestJpa> findInvestPostsByFilters(
        String investStyleForFiltering,
        String stockItemNameForFiltering,
        String investmentOpinionForFiltering,
        String investmentProcessForFiltering
	 );
}

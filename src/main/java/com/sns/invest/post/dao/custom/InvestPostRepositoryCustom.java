package com.sns.invest.post.dao.custom;

import java.util.List;

import com.sns.invest.post.model.invest.InvestPost;

public interface InvestPostRepositoryCustom {
	 List<InvestPost> findInvestPostsByFilters(
        String investStyleForFiltering,
        String stockItemNameForFiltering,
        String investmentOpinionForFiltering,
        String investmentProcessForFiltering
	 );
}

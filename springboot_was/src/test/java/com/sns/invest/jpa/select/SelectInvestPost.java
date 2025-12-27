package com.sns.invest.jpa.select;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import com.sns.invest.config.QueryDslCofig;
import com.sns.invest.post.dao.InvestPostRepository;
import com.sns.invest.user.dao.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
//@ActiveProfiles("test") 
@Import(QueryDslCofig.class) 
public class SelectInvestPost {

	@Autowired InvestPostRepository investPostRepository;
	
	@Test
	public void aa() {
		System.out.println(investPostRepository.findImagePathById(15));	
	}
}

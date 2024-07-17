package com.sns.invest.jpa.select;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import com.sns.invest.config.QueryDslCofig;
import com.sns.invest.user.dao.UserRepository;
import com.sns.invest.user.model.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@ActiveProfiles("test") // 이걸로 test설정으로 됨
@Import(QueryDslCofig.class) // 이거 없으면 안됨
public class SelectUser {
	
	@Autowired UserRepository userRepository;
	
	@Test
	public void aa() {
		User user = userRepository.findById(1);

		user = userRepository.findById(4); // select결과 없으면 null찍힘
		System.out.println(user);

		
	}
}

package com.sns.invest.jpa.insert;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import com.sns.invest.config.QueryDslCofig;
import com.sns.invest.user.dao.UserRepository;
import com.sns.invest.user.model.UserJpa;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@ActiveProfiles("test") // 이걸로 test설정으로 됨
@Import(QueryDslCofig.class) // 이거 없으면 안됨
public class InsertUser {

	@Autowired UserRepository userRepository;
	
	@Test
	public void signUp() {
		UserJpa user = UserJpa.builder()
				.username("loginId").password("encryptPassword")
				.nickName("nickName").email("email").role("user")
				.build();
		
		UserJpa aa = userRepository.save(user);
		
//		System.out.println(aa);
		// 뭘 테스트 할지
	}
	
}

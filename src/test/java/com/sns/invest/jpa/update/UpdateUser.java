package com.sns.invest.jpa.update;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import com.sns.invest.config.QueryDslCofig;
import com.sns.invest.user.dao.UserRepository;
import com.sns.invest.user.dao.model.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@ActiveProfiles("test") 
@Import(QueryDslCofig.class) 
public class UpdateUser {

	@Autowired UserRepository userRepository;
	
	@Test
	public void aa() {
		User user = userRepository.findById(1); // 조회한 결과 값만 바꿔주면 자동 업데이트 됨
		user.updateLocation("위치 ");
	}
}

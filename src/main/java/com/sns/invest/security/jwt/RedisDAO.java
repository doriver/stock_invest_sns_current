package com.sns.invest.security.jwt;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RedisDAO {

	private final RedisTemplate<String, Object> redisTemplate03;

    public void saveWithTTL(String key, Object value, long ttl, TimeUnit timeUnit) {
    	redisTemplate03.opsForValue().set(key, value); // set : key-value형태로 저장    
    	redisTemplate03.expire(key, ttl, timeUnit); // TTL을 설정 , TimeUnit.SECONDS, TimeUnit.MINUTES
    }
	
	// get : key에 해당하는 value값 얻음
	public String get(String key) {
		return (String)redisTemplate03.opsForValue().get(key);
	}

}

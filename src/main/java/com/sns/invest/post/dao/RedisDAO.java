package com.sns.invest.post.dao;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RedisDAO {
    
    private final RedisTemplate<String, Object> redisTemplate;
    
    // sadd : 집합에 value추가
    public Long sadd(String key, String value) {
        return redisTemplate.opsForSet().add(key, value);
    }
    // scard : 집합 원소개수 얻믕
    public Long scard(String key) {
        return redisTemplate.opsForSet().size(key);
    }
    // sismember : 집합에 value값 존재하는지 확인
    public Boolean sismember(String key, String value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }
    // srem : 집합에서 해당원소 제거
    public Long srem(String key, String value) {
        return redisTemplate.opsForSet().remove(key, value);
    }
    // del : 해당key 데이터 삭제
    public void del(String key) {
        redisTemplate.delete(key);
    }    
}

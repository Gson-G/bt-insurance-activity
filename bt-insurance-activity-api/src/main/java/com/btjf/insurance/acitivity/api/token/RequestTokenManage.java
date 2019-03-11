package com.btjf.insurance.acitivity.api.token;

import com.bz.ins.activity.exception.ActivityException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author kantenmei
 * @date 2019/3/11
 * @time 5:24 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Component
public class RequestTokenManage {

    public static final String ACTIVITY_REQUEST_TOKEN_CACHE_KEY = "activity_request_token_cache_key_";

    @Resource
    private RedisTemplate<String, String> redisTemplate;


    public void put(String requstToken, long expTime){
        redisTemplate.opsForHash().put(ACTIVITY_REQUEST_TOKEN_CACHE_KEY, requstToken, expTime);
    }

    public void delete(String requstToken){
        redisTemplate.opsForHash().delete(ACTIVITY_REQUEST_TOKEN_CACHE_KEY, requstToken);
    }

    public Object getAndValidate(String requstToken){
       Object expTime = redisTemplate.opsForHash().get(ACTIVITY_REQUEST_TOKEN_CACHE_KEY, requstToken);
       if (null == expTime) {
           throw new ActivityException("requestToken 无效");
       }
//       long current = System.currentTimeMillis();
//       if (current > (long) expTime) {
//           throw new ActivityException("requestToken 过期超时");
//       }
       delete(requstToken);
       return expTime;
    }


    public String getAndCacheRequestToken() {
        String requestToken = UUID.randomUUID().toString();
        long expTime = System.currentTimeMillis() + 1000 * 60 * 5;
        put(requestToken, expTime);
        return requestToken;
    }

}

package com.bz.ins.activity.util;

import com.bz.ins.common.utils.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author kantenmei
 * @date 2019/3/8
 * @time 4:46 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
@Component
public class CommonRedisHelper {

    public static final String LOCK_PREFIX = "redis_lock";
    public static final int LOCK_EXPIRE = 300; // ms

    public static final Random RANDOM = new Random();

    private static final Logger logger = LoggerFactory.getLogger(CommonRedisHelper.class);

    @Resource
    private RedisTemplate redisTemplate;


    public void releaseLock(String key, RedisLockResult redisLockResult) {
        String lock = LOCK_PREFIX + key;
        String result = (String) redisTemplate.opsForValue().get(lock);
        if (redisLockResult.getLockedKey().equals(result)) {
            redisTemplate.opsForValue().getOperations().delete(lock);
        }
    }


    public RedisLockResult lock(String key, long expTime) {
        RedisLockResult result;
        long expEndTime = System.currentTimeMillis() + expTime;
        String locResultKey = UUID.randomUUID().toString() + Thread.currentThread().getName();
        try {
            while (System.currentTimeMillis() < expEndTime) {
                result = doGetLock(key, locResultKey);
                if (result.isLockSuccess()) {
                    return result;
                }
                Thread.sleep(5L, RANDOM.nextInt(999998));
            }
        } catch (InterruptedException e) {
            logger.error("redis 锁获取出错");
            throw new RuntimeException("redis 锁获取出错");
        }
        return new RedisLockResult(" ", false);
    }

    private RedisLockResult doGetLock(String key, String locResultKey) {
        String lock = LOCK_PREFIX + key;

        Boolean isLocked = (Boolean) redisTemplate.execute((RedisCallback) connection -> {

            Expiration expiration = Expiration.seconds(3);
            Boolean acquire = connection.set(lock.getBytes(), locResultKey.getBytes(),
                    expiration, RedisStringCommands.SetOption.SET_IF_ABSENT);
            return acquire;
        });
        return new RedisLockResult(locResultKey, isLocked);
    }


    /**
     * 删除锁
     *
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

}

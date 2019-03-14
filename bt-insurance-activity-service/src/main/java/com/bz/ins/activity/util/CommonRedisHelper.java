package com.bz.ins.activity.util;

import com.bz.ins.activity.exception.ActivityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author kantenmei
 * @date 2019/3/8
 * @time 4:46 PM
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 *
 *
 *
 *         RedisLockResult lock = commonRedisHelper.lock(key, 3000l);
 *         try {
 *             if (lock.isLockSuccess()) {
 *                 dobusiness()
 *             } else {
 *                 throw new TimeOutException("网络繁忙请稍后重试");
 *             }
 *         } finally {
 *             if (lock.isLockSuccess()) {
 *                 commonRedisHelper.releaseLock(key, lock);
 *             }
 *         }
 *
 *
 */
@Component
public class CommonRedisHelper {

    public static final String LOCK_PREFIX = "redis_lock";

    public static final Random RANDOM = new Random();

    private static final Logger logger = LoggerFactory.getLogger(CommonRedisHelper.class);

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 加锁
     * @param key key
     * @param expTime 加锁自旋时间
     * @return
     */
    public RedisLockResult lock(String key, long expTime) {
        RedisLockResult result;
        long expEndTime = System.currentTimeMillis() + expTime;
        String locResultKey = UUID.randomUUID().toString() + Thread.currentThread().getName();
        try {
            while (System.currentTimeMillis() < expEndTime) {
                //自旋
                result = doGetLock(key, locResultKey);
                if (result.isLockSuccess()) {
                    return result;
                }
                Thread.sleep(5L, RANDOM.nextInt(999998));
            }
        } catch (InterruptedException e) {
            logger.error("redis 锁获取出错");
            throw new ActivityException("redis 锁获取出错");
        }
        return new RedisLockResult(" ", false);
    }

    /**
     * 释放锁
     * @param key
     * @param redisLockResult
     */
    public void releaseLock(String key, RedisLockResult redisLockResult) {
        //释放的时候判断是否是自己加的锁
        String lock = LOCK_PREFIX + key;
        String result = (String) redisTemplate.execute((RedisCallback) connection -> {
            byte[] acquire = connection.get(lock.getBytes());
            if (null != acquire && acquire.length > 0) {
                return new String(acquire);
            }
            return null;
        });
        if (redisLockResult.getLockedKey().equals(result)) {
            redisTemplate.execute((RedisCallback) connection -> connection.del(lock.getBytes()));
        }
    }

    private RedisLockResult doGetLock(String key, String locResultKey) {
        String lock = LOCK_PREFIX + key;

        Boolean isLocked = (Boolean) redisTemplate.execute((RedisCallback) connection -> {
            //3秒钟的锁持有时间 当前业务够用了, 后面考虑实现续约
            Expiration expiration = Expiration.seconds(600);
            Boolean acquire = connection.set(lock.getBytes(), locResultKey.getBytes(),
                    expiration, RedisStringCommands.SetOption.SET_IF_ABSENT);
            return acquire;
        });
        return new RedisLockResult(locResultKey, isLocked);
    }
}

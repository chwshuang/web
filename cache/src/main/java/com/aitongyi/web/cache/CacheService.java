package com.aitongyi.web.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 缓存服务
 * Created by admin on 16/8/18.
 */
@Component
public class CacheService {
    @Autowired
    private JedisPool jedisPool;

    /**
     * 设置缓存对象
     * @param key
     * @param value
     */
    public void set(String key,String value){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
    }

    /**
     * 获取缓存对象
     * @param key
     * @return
     */
    public String get(String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
    }

    /**
     * 删除缓存对象
     * @param key
     */
    public void del(String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.del(key);
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
    }

}

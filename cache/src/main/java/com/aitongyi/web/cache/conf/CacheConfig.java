package com.aitongyi.web.cache.conf;

/**
 * Created by hushuang on 16/8/18.
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.aitongyi.web.cache"})
public class CacheConfig {
    /** redis缓存服务器地址    */
    @Value("${redis.host}")
    private String host;
    /** redis缓存服务器端口    */
    @Value("${redis.port}")
    private Integer port;
    /** redis缓存服务器连接超时时间    */
    @Value("${redis.timeout}")
    private Integer timeout;

    @Bean(name = "jedisPool")
    public JedisPool jedispool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxWaitMillis(30000); //  最大等待时间
        config.setMaxTotal(32);         //  最大连接数
        config.setMinIdle(6);           //  允许最小的空闲连接数
        config.setTestOnBorrow(false);  //  申请到连接时是否效验连接是否有效,对性能有影响,建议关闭
        config.setTestOnReturn(false);  //  使用完连接放回连接池时是否效验连接是否有效,对性能有影响,建议关闭
        config.setTestWhileIdle(true);  //  申请到连接时,如果空闲时间大于TimeBetweenEvictionRunsMillis时间,效验连接是否有效,建议开启,对性能有效不大
        config.setTimeBetweenEvictionRunsMillis(30000); //TestWhileIdle的判断依据
        return new JedisPool(config, host, port, timeout);
    }
}


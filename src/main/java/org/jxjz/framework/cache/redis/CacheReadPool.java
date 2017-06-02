package org.jxjz.framework.cache.redis;

import org.jxjz.base.spring.ApplicationFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
/**
 * 连接池基类（读）
 */
public class CacheReadPool {
    public static JedisPool pool;
    static String cache_name="redis_read";

    static{
        pool=(JedisPool)ApplicationFactory.getBean(cache_name);
        System.out.println(cache_name+" init");
    }
    /**
     * 得到一个jedis连接，默认是索引库0
     * @return jedis连接
     */
    public Jedis getCachePool(){
        return pool.getResource();
    }
    /**
     * 得到一个索引库为db的jedis连接
     * @param db
     * @return jedis连接
     */
    public Jedis getCachePool(int db){
        Jedis jedis=pool.getResource();
        jedis.select(db);
        return jedis;
    }
    /**
     * 回收jedis连接
     * @param jedis
     */
    public void returnCachePool(Jedis jedis){
        if(jedis!=null){
            pool.returnResource(jedis);
        }
    }
    /**
     * 回收jedis连接，异常时使用
     * @param jedis
     */
    public void returnBrokenResource(Jedis jedis){
        pool.returnBrokenResource(jedis);
    }
}

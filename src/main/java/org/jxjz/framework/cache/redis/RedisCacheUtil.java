package org.jxjz.framework.cache.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jxjz.framework.util.LogUtil;

import redis.clients.jedis.Jedis;
/**
 * 【功能说明】：Redis  工具类
 * @author lihaijun
 * 【创建时间】2017-05-25
 */
public class RedisCacheUtil <V extends Serializable> {

	private static final Logger log= LogUtil.getLogger();
    public int DB=0;

    /**
     * 通过KEY到得对应VALUE
     * @return 返回序列化对象
     */
    public V get(String key) {
        CacheReadPool cacheReadPool=new CacheReadPool();
        Jedis jedis=cacheReadPool.getCachePool();
        V v=null;
        try{
            v=byte2Object(jedis.get(getKey(key)));
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheReadPool.returnBrokenResource(jedis);
        }finally {
            cacheReadPool.returnCachePool(jedis);
        }
        return v;
    }

    /**
     * 通过KEY和JEDIS连接取得对应值
     * @return 返回序列化对象
     */
    public V get(String key, Jedis jedis) {
        return byte2Object(jedis.get(getKey(key)));
    }

    /****************Hash操作*************************************************************************/
    /**
     * 保存map信息
     */
    public void saveMap(String key, HashMap  hash) {
        CacheWritePool cacheWritePool=new CacheWritePool();
        Jedis jedis=cacheWritePool.getCachePool(DB);
        try{
        	jedis.hmset(key, hash);
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheWritePool.returnBrokenResource(jedis);
        }finally {
            cacheWritePool.returnCachePool(jedis);
        }
    }
    
    /**
     * 向名称为key的hash中添加元素field<—>value
     */
    public void saveMap(String key, String field, String value) {
        CacheWritePool cacheWritePool=new CacheWritePool();
        Jedis jedis=cacheWritePool.getCachePool(DB);
        try{
            saveMap(key,field,value,jedis);
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheWritePool.returnBrokenResource(jedis);
        }finally {
            cacheWritePool.returnCachePool(jedis);
        }
    }

    /**
     * 在指定jedis连接下向名称为key的hash中添加元素field<—>value
     */
    public void saveMap(String key, String field, String value, Jedis jedis) {
        jedis.hset(key,field,value);
    }

    /**
     * 向名称为key的hash中添加元素field<—>value,并设置key超时时间seconds
     */
    public void saveMap(String key, String field, String value, int seconds) {
        CacheWritePool cacheWritePool=new CacheWritePool();
        Jedis jedis=cacheWritePool.getCachePool(DB);
        try{
            saveMap(key,field,value,seconds,jedis);
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheWritePool.returnBrokenResource(jedis);
        }finally {
            cacheWritePool.returnCachePool(jedis);
        }
    }

    /**
     * 在指定jedis连接下向名称为key的hash中添加元素field<—>value,并设置key超时时间seconds
     */
    public void saveMap(String key, String field, String value, int seconds, Jedis jedis) {
        saveMap(key, field, value, jedis);
        if(seconds>0){
            jedis.expire(key,seconds);
        }
    }

    /**
     * 删除名称为key的hash中键为field的域

     * @param field
     */
    public void removeMap(String key,String field){
        CacheWritePool cacheWritePool=new CacheWritePool();
        Jedis jedis=cacheWritePool.getCachePool(DB);
        try{
            jedis.hdel(key,field);
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheWritePool.returnBrokenResource(jedis);
        }finally {
            cacheWritePool.returnCachePool(jedis);
        }
    }
    /**
     * 通过KEY取得缓存中MAP对象
     * @return Map对象
     */
    public Map<String, String> getAllMap(String key) {
        CacheReadPool cacheReadPool=new CacheReadPool();
        Jedis jedis=cacheReadPool.getCachePool(DB);
        Map<String,String> map=null;
        try{
            map=getAllMap(key,jedis);
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheReadPool.returnBrokenResource(jedis);
        }finally {
            cacheReadPool.returnCachePool(jedis);
        }
        return map;
    }

    /**
     * 通过KEY和JEDIS连接取得MAP对象
     * @return 返回Map对象
     */
    public Map<String, String> getAllMap(String key, Jedis jedis) {
        return jedis.hgetAll(key);
    }
    /**
     * 返回名称为key的hash中field对应的value

     * @param field
     * @return 返回该filed对应的值
     */
   
    public String getMapStr(String key, String field) {
        CacheReadPool cacheReadPool=new CacheReadPool();
        Jedis jedis=cacheReadPool.getCachePool(DB);
        String value="";
        try{
            value=getMapStr(key,field,jedis);
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheReadPool.returnBrokenResource(jedis);
        }finally {
            cacheReadPool.returnCachePool(jedis);
        }
        return value;
    }

    /**
     * 在指定jedis连接下返回名称为key的hash中field对应的value
     * @return 返回该field对应的值
     */
   
    public String getMapStr(String key, String field, Jedis jedis) {
        return jedis.hget(key,field);
    }
    /****************保存操作*************************************************************************/
    /**
     * 将KEY对应的值Value写入缓存
     */
    public void save(String key, V value) {
        CacheWritePool cacheWritePool=new CacheWritePool();
        Jedis jedis=cacheWritePool.getCachePool(DB);
        try{
            jedis.set(getKey(key), object2Bytes(value));
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheWritePool.returnBrokenResource(jedis);
        }finally {
            cacheWritePool.returnCachePool(jedis);
        }
    }

    /**
     * 在指定的JEDIS连接下将KEY对应的VALUE写入缓存
     */
    public void save(String key, V value, Jedis jedis) {
        jedis.set(getKey(key), object2Bytes(value));
    }

    /**
     * 将KEY对应的值Value写入缓存，并设置该数据超时时间 seconds
     * @param seconds 超时时间（单位秒）
     */
    public void save(String key,int seconds, V value) {
        CacheWritePool cacheWritePool=new CacheWritePool();
        Jedis jedis=cacheWritePool.getCachePool(DB);
        try{
            jedis.setex(getKey(key),seconds,object2Bytes(value));
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheWritePool.returnBrokenResource(jedis);
        }finally {
            cacheWritePool.returnCachePool(jedis);
        }
    }

    /**
     * 将KEY对应的值Value写入缓存，并设置该数据超时时间 seconds
     * @param seconds 超时时间（单位秒）
     */
    public void save(String key,int seconds, String  value) {
        CacheWritePool cacheWritePool=new CacheWritePool();
        Jedis jedis=cacheWritePool.getCachePool(DB);
        try{
            jedis.setex(key,seconds,value);
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheWritePool.returnBrokenResource(jedis);
        }finally {
            cacheWritePool.returnCachePool(jedis);
        }
    }   
    
    
    
    
    /**
     * 在指定的JEDIS连接下将KEY对应的值Value写入缓存，并设置该数据超时时间 seconds
     * @param seconds 超时时间（单位秒）
     */
    public void save(String key,int seconds, V value,Jedis jedis) {
        jedis.setex(getKey(key),seconds,object2Bytes(value));
    }
    
    
    
    
    /**
     * 得到指定KEY对应的VALUE
     * @return 返回对应的值
     */
    public String getStr(String key) {
        CacheReadPool cacheReadPool=new CacheReadPool();
        Jedis jedis=cacheReadPool.getCachePool(DB);
        String value="";
        try{
        	
            value=jedis.get(key);
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheReadPool.returnBrokenResource(jedis);
        }finally {
            cacheReadPool.returnCachePool(jedis);
        }
        return value;
    }

    /**
     * 在指定JEDIS连接下得到KEY对应的VALUE
     */
    public String getStr(String key, Jedis jedis) {
        return jedis.get(key);
    }    
    
    
    /****************删除操作*************************************************************************/
    /**
     * 删除缓存中指定KEY
     */
    public void remove(String key) {
        CacheWritePool cacheWritePool=new CacheWritePool();
        Jedis jedis=cacheWritePool.getCachePool(DB);
        try{
            jedis.del(getKey(key));
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheWritePool.returnBrokenResource(jedis);
        }finally {
            cacheWritePool.returnCachePool(jedis);
        }
    }

    /**
     * 删除count个名称为key的list中值为value的元素。
     * count为0，删除所有值为value的元素，
     * count>0从头至尾删除count个值为value的元素，
     * count<0从尾到头删除|count|个值为value的元素。
     */
    public void removeChildInList(String key,int count,String value){
         CacheWritePool cacheWritePool=new CacheWritePool();
        Jedis jedis=cacheWritePool.getCachePool(DB);
        try{
            jedis.lrem(key,count,value);
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheWritePool.returnBrokenResource(jedis);
        }finally {
            cacheWritePool.returnCachePool(jedis);
        }
    }
    /**
     * 在指定的JEDIS连接下删除缓存中指定KEY
     */
   
    public void remove(String key, Jedis jedis) {
        jedis.del(getKey(key));
    }





    /**
     * 给数据库中名称为key的string赋予值value
     */
    public void saveStr(String key, String value) {
        CacheWritePool cacheWritePool=new CacheWritePool();
        Jedis jedis=cacheWritePool.getCachePool(DB);
        try{
            jedis.set(key, value);
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheWritePool.returnBrokenResource(jedis);
        }finally {
            cacheWritePool.returnCachePool(jedis);
        }
    }
    
    /**
     * 向库中添加string（名称为key，值为value）同时，设定过期时间seconds
     */
    public void saveStr(String key, String value,int seconds) {
        CacheWritePool cacheWritePool=new CacheWritePool();
        Jedis jedis=cacheWritePool.getCachePool(DB);
        try{
            jedis.setex(key,seconds, value);
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheWritePool.returnBrokenResource(jedis);
        }finally {
            cacheWritePool.returnCachePool(jedis);
        }
    }

    /**
     * 在指定jedis连接下向库中添加string（名称为key，值为value）同时，设定过期时间seconds
     */
    public void saveStr(String key, String value,Jedis jedis,int seconds) {
        jedis.setex(key,seconds, value);
    }

    /**
     * 在指定jedis连接下给数据库中名称为key的string赋予值value
     */
    public void saveStr(String key, String value, Jedis jedis) {
        jedis.set(key, value);
    }


    /**
     * 给数据库中名称为key的string赋予值value
     */
    public void updateStr(String key, String value) {
        saveStr(key, value);
    }

    /**
     * 在指定jedis连接下给数据库中名称为key的string赋予值value
     */
   
    public void updateStr(String key, String value, Jedis jedis) {
        saveStr(key, value,jedis);
    }

    /**
     * 返回当前数据库中key的数目
     * @param DB 索引
     * @return 当索引前库中key的数目
     */
   
    public long getDbSize(int DB) {
        CacheReadPool cacheReadPool=new CacheReadPool();
        Jedis jedis=cacheReadPool.getCachePool(DB);
        long size=0;
        try{
            size=jedis.dbSize();
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheReadPool.returnBrokenResource(jedis);
        }finally {
            cacheReadPool.returnCachePool(jedis);
        }
        return size;
    }

    /**
     * 删除一个key
     */
    public void removeStr(String key) {
        CacheWritePool cacheWritePool=new CacheWritePool();
        Jedis jedis=cacheWritePool.getCachePool(DB);
        try{
            jedis.del(key);
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheWritePool.returnBrokenResource(jedis);
        }finally {
            cacheWritePool.returnCachePool(jedis);
        }

    }
 

    /**
     * 切换库索引
     */
    public void selectDB(int db) {
        DB=db;
    }

    /**
     * 将当前数据库中old_db的key转移到有new_db索引的数据库
     */
   
    public void move(String key, int old_db, int new_db) {
        CacheWritePool cacheWritePool=new CacheWritePool();
        Jedis jedis=cacheWritePool.getCachePool(old_db);
        try{
            jedis.move(key,new_db);
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheWritePool.returnBrokenResource(jedis);
        }finally {
            cacheWritePool.returnCachePool(jedis);
        }
    }


    /**
     * 删除当前选择数据库中的所有key
     */
    public void flushDB() {
        CacheWritePool cacheWritePool=new CacheWritePool();
        Jedis jedis=cacheWritePool.getCachePool(DB);
        try{
            jedis.flushDB();
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheWritePool.returnBrokenResource(jedis);
        }finally {
            cacheWritePool.returnCachePool(jedis);
        }
    }

    /**
     * 在指定Jedis连接下删除当前选择数据库中的所有key
     */
   
    public void flushDB(Jedis jedis) {
        jedis.flushDB();
    }

    /**
     * 对字符串转换成二进制数
     * @return key的二进制数
     */
    private byte[] getKey(String key) {
        return key.getBytes();
    }

    /**
     * 名称为key的string增1操作
     * @return 返回当前key的结果
     */
   
    public Long incr(String key) {
        CacheWritePool cacheWritePool=new CacheWritePool();
        Jedis jedis=cacheWritePool.getCachePool(DB);
        long l=0;
        try{
            l=jedis.incr(key);
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheWritePool.returnBrokenResource(jedis);
        }finally {
            cacheWritePool.returnCachePool(jedis);
        }
        return l;
    }

    /**
     * 在指定jedis连接下对名称为key的string增1操作
     * @return 返回当前key的结果
     */
    public Long incr(String key, Jedis jedis) {
        return jedis.incr(key);
    }

    /**
     * 名称为key的string减1操作
     */
    public Long decr(String key) {
        CacheWritePool cacheWritePool=new CacheWritePool();
        Jedis jedis=cacheWritePool.getCachePool(DB);
        long l=0;
        try{
            l=jedis.decr(key);
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheWritePool.returnBrokenResource(jedis);
        }finally {
            cacheWritePool.returnCachePool(jedis);
        }
        return l;
    }

    /**
     * 在指定jedis连接下对名称为key的string减1操作
     * @param jedis
     * @return 返回当前key的结果
     */
    public Long decr(String key, Jedis jedis) {
        return jedis.decr(key);
    }

    /**
     * 返回满足给定pattern的所有key，慎用
     * @return 满足条件key的集合
     */
    public Set<String> keys(String pattern){
        CacheReadPool cacheReadPool=new CacheReadPool();
        Jedis jedis=cacheReadPool.getCachePool(DB);
        Set<String> key_set=null;
        try{
            key_set=jedis.keys(pattern);
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheReadPool.returnBrokenResource(jedis);
        }finally {
            cacheReadPool.returnCachePool(jedis);
        }
        return key_set;
    }

    /**
     * 在指定jedis连接下返回满足给定pattern的所有key
     * @return 满足条件key的集合
     */
    public Set<String> keys(String pattern, Jedis jedis) {
        return jedis.keys(pattern);
    }
    
    /**
     * 在名称为key的list头添加一个值为oneValue的 元素
     */
    public void addHeadList(String key, String oneValue) {
        CacheWritePool cacheWritePool=new CacheWritePool();
        Jedis jedis=cacheWritePool.getCachePool(DB);
        try{
            jedis.lpush(key, oneValue);
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheWritePool.returnBrokenResource(jedis);
        }finally {
            cacheWritePool.returnCachePool(jedis);
        }
    }
    
    /**
     * 将KEY对应的值Value写入缓存
     */
    public void addHeadList(String key, V value) {
        CacheWritePool cacheWritePool=new CacheWritePool();
        Jedis jedis=cacheWritePool.getCachePool(DB);
        try{
            jedis.lpush(getKey(key), object2Bytes(value));
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheWritePool.returnBrokenResource(jedis);
        }finally {
            cacheWritePool.returnCachePool(jedis);
        }
    } 
    
    
    /**
     * 在指定Key关联的链表中，删除前count个值等于value的元素。
     * 如果count大于0，从头向尾遍历并删除，如果count小于0，则从尾向头遍历并删除。
     * 如果count等于0，则删除链表中所有等于value的元素。
     * 如果指定的Key不存在，则直接返回0。
     */
    public boolean lrem(String key,String value){
    	CacheWritePool cacheWritePool=new CacheWritePool();
    	Jedis jedis=cacheWritePool.getCachePool(DB);
        try{
        	jedis.lrem(key, 1, value);
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
        	cacheWritePool.returnBrokenResource(jedis);
        	return false;
        }finally {
        	cacheWritePool.returnCachePool(jedis);
        }
        return true;
    }
    
    

    /**
     * 返回名称为key的list中page至list_num之间的元素（下标从0开始，下同）
     * @param list_num
     * @return list集合
     */
    public List<String> lrange(String key,int page,int list_num){
        CacheReadPool cacheReadPool=new CacheReadPool();
        Jedis jedis=cacheReadPool.getCachePool(DB);
        List<String> l=new ArrayList<String>();
        try{
            l=jedis.lrange(key, page, list_num);
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheReadPool.returnBrokenResource(jedis);
        }finally {
            cacheReadPool.returnCachePool(jedis);
        }
        return l;
    }

    /**
     * 在指定jedis连接下返回名称为key的list中page至list_num之间的元素（下标从0开始，下同）
     * @param list_num
     * @param jedis
     * @return list集合
     */
    public List<String> lrange(String key, int page, int list_num, Jedis jedis) {
        List<String> l=jedis.lrange(key, page, list_num);
        return l;
    }

    /**
     * 在指定jedis连接下返回名称为key的list中page至list_num之间的元素（下标从0开始，下同）
     * @param list_num
     * @param jedis
     * @return list集合
     */
    public List lrangeList(String key, int page, int list_num) {
    	CacheReadPool cacheReadPool=new CacheReadPool();
    	Jedis jedis=cacheReadPool.getCachePool(DB);
        try{
            
            List list= jedis.lrange(key, page, list_num); 
            return  list;
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheReadPool.returnBrokenResource(jedis);
        }finally {
            cacheReadPool.returnCachePool(jedis);
        }
        return  null;
    }

    /**
     * 确认一个key是否存在
     */
    public boolean exists(String key){
        CacheReadPool cacheReadPool=new CacheReadPool();
        Jedis jedis=cacheReadPool.getCachePool(DB);
        boolean flg=false;
        try{
            flg=jedis.exists(key);
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheReadPool.returnBrokenResource(jedis);
        }finally {
            cacheReadPool.returnCachePool(jedis);
        }
        return flg;
    }

    /**
     * 在指定jedis连接下确认一个key是否存在
     */
    public boolean exists(String key, Jedis jedis) {
        return jedis.exists(key);
    }

    /**
     * 名称为key的hash中是否存在键为field的域
     */
    public boolean existsMap(String key,String field){
        CacheReadPool cacheReadPool=new CacheReadPool();
        Jedis jedis=cacheReadPool.getCachePool(DB);
        boolean flg=false;
        try{
            flg=jedis.hexists(key,field);
        }catch (Exception e){
        	log.error("error:"+e.getMessage(),e);
            cacheReadPool.returnBrokenResource(jedis);
        }finally {
            cacheReadPool.returnCachePool(jedis);
        }
        return flg;
    }

    /**
     * 在指定jedis连接下名称为key的hash中是否存在键为field的域
     */
    public boolean existsMap(String key,String field, Jedis jedis) {
        return jedis.hexists(key,field);
    }


    
    @SuppressWarnings("unchecked")
    public V byte2Object(byte[] bytes) {
        if (bytes == null || bytes.length == 0)
            return null;

        try {
            ObjectInputStream inputStream;
            inputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
            Object obj = inputStream.readObject();

            return (V) obj;
        } catch (IOException e) {
        	log.error("error:"+e.getMessage(),e); 
        } catch (ClassNotFoundException e) {
        	log.error("error:"+e.getMessage(),e);
        }

        return null;
    }

    public byte[] object2Bytes(V value) {
        if (value == null)
            return null;
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream;
        try {
            outputStream = new ObjectOutputStream(arrayOutputStream);

            outputStream.writeObject(value);
        } catch (IOException e) {
        	log.error("error:"+e.getMessage(),e);
        } finally {
            try {
                arrayOutputStream.close();
            } catch (IOException e) {
            	log.error("error:"+e.getMessage(),e);
            }
        }
        return arrayOutputStream.toByteArray();
    }

}

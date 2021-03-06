//package com.liu.util.redis;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.data.redis.core.*;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import java.io.Serializable;
//import java.util.List;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author yys
// * Description:
// * @date created in 10:01 2018-03-28
// * Modified by:
// */
//@Service
//public class RedisTemplateService1{
//
////    @Resource(name = "redisTemplateObject1")
//    @Autowired(required = false)
//    @Qualifier("redisTemplateObject1")
//    private RedisTemplate redisTemplate;
//
//
//    public RedisTemplateService1 saveKeyVal(String key, String value) {
//        redisTemplate.opsForValue().set(key, value);
//        return this;
//    }
//
//    public String getValByKey(String key) {
//        try {
//            Object val = redisTemplate.opsForValue().get(key);
//            if(val == null){
//                return "";
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return "";
//        }
//        return redisTemplate.opsForValue().get(key).toString();
//    }
//
//    public RedisTemplateService1 saveKeySet(String key, Set<String> set) {
//        redisTemplate.delete(key);
//        redisTemplate.opsForSet().add(key, set);
//        return this;
//    }
//
//    /**
//     * 指定缓存失效时间
//     * @param key 键
//     * @param time 时间(秒)
//     * @return
//     */
//    public boolean expire(String key,long time){
//        try {
//            if(time>0){
//                redisTemplate.expire(key, time, TimeUnit.SECONDS);
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public void delValByKey(String key) {
//        if(!StringUtils.isEmpty(key)){
//            redisTemplate.delete(key);
//        }
//    }
//
//    public Set getSet(String key) {
//        Object object = redisTemplate.opsForSet().randomMember(key);
//        if(object != null && object instanceof Set){
//            return (Set)object;
//        }
//        return null;
//    }
//
//    public boolean isSetExistVal(String key, String value) {
//        Object object = redisTemplate.opsForSet().randomMember(key);
//        if(object != null && object instanceof Set){
//            return ((Set)object).contains(value);
//        }
//        return false;
//    }
//
//    public boolean isExistKey(String key) {
//        Boolean temp = redisTemplate.hasKey(key);
//        if(temp != null){
//            return temp;
//        }else{
//            return false;
//        }
//    }
//
//
//
//
//    public org.springframework.data.redis.core.ValueOperations opsForValue(){
//        return redisTemplate.opsForValue();
//    }
//
//
//
//
//
//    /**
//     * 写入缓存
//     * @param key
//     * @param value
//     * @return
//     */
//    public boolean set(final String key, Object value) {
//        boolean result = false;
//        try {
//            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
//            operations.set(key, value);
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * 写入缓存设置时效时间
//     * @param key
//     * @param value
//     * @return
//     */
//    public boolean set(final String key, Object value, Long expireTime) {
//        boolean result = false;
//        try {
//            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
//            operations.set(key, value);
//            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * 批量删除对应的value
//     * @param keys
//     */
//    public void remove(final String... keys) {
//        for (String key : keys) {
//            remove(key);
//        }
//    }
//
//    /**
//     * 批量删除key
//     * @param pattern
//     */
//    public void removePattern(final String pattern) {
//        Set<Serializable> keys = redisTemplate.keys(pattern);
//        if (keys.size() > 0)
//            redisTemplate.delete(keys);
//    }
//
//    /**
//     * 删除对应的value
//     * @param key
//     */
//    public void remove(final String key) {
//        if (exists(key)) {
//            redisTemplate.delete(key);
//        }
//    }
//
//    /**
//     * 判断缓存中是否有对应的value
//     * @param key
//     * @return
//     */
//    public boolean exists(final String key) {
//        return redisTemplate.hasKey(key);
//    }
//
//    /**
//     * 读取缓存
//     * @param key
//     * @return
//     */
//    public Object get(final String key) {
//        Object result = null;
//        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
//        result = operations.get(key);
//        return result;
//    }
//
//    /**
//     * 哈希 添加带时效性
//     * @param key
//     * @param hashKey
//     * @param value
//     */
//    public boolean hmSetTime(String key, Object hashKey, Object value,Long expireTime){
//        boolean result = false;
//        try {
//            HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
//            hash.put(key,hashKey,value);
//            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * 哈希 添加带时效性
//     * @param key
//     * @param hashKey
//     * @param value
//     */
//    public void hmSet(String key, Object hashKey, Object value){
//            HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
//            hash.put(key,hashKey,value);
//    }
//    /**
//     * 哈希获取数据
//     * @param key
//     * @param hashKey
//     * @return
//     */
//    public Object hmGet(String key, Object hashKey){
//        HashOperations<String, Object, Object>  hash = redisTemplate.opsForHash();
//        return hash.get(key,hashKey);
//    }
//
//    /**
//     * 列表添加
//     * @param k
//     * @param v
//     */
//    public void lPush(String k,Object v){
//        ListOperations<String, Object> list = redisTemplate.opsForList();
//        list.rightPush(k,v);
//    }
//
//    /**
//     * 列表获取
//     * @param k
//     * @param l
//     * @param l1
//     * @return
//     */
//    public List<Object> lRange(String k, long l, long l1){
//        ListOperations<String, Object> list = redisTemplate.opsForList();
//        return list.range(k,l,l1);
//    }
//
//    /**
//     * 集合添加
//     * @param key
//     * @param value
//     */
//    public void add(String key,Object value){
//        SetOperations<String, Object> set = redisTemplate.opsForSet();
//        set.add(key,value);
//    }
//
//    /**
//     * 集合获取
//     * @param key
//     * @return
//     */
//    public Set<Object> setMembers(String key){
//        SetOperations<String, Object> set = redisTemplate.opsForSet();
//        return set.members(key);
//    }
//
//    /**
//     * 有序集合添加
//     * @param key
//     * @param value
//     * @param scoure
//     */
//    public void zAdd(String key,Object value,double scoure){
//        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
//        zset.add(key,value,scoure);
//    }
//
//    /**
//     * 有序集合获取
//     * @param key
//     * @param scoure
//     * @param scoure1
//     * @return
//     */
//    public Set<Object> rangeByScore(String key,double scoure,double scoure1){
//        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
//        return zset.rangeByScore(key, scoure, scoure1);
//    }
//
//}
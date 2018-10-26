package com.wjy.jedis;

import com.wjy.mq.ps.JedisPubSubListener;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author ybxxszl
 * @date 2018年10月10日
 * @description Redis工具类
 */
public class RedisUtil {

    /**
     * @param key     键
     * @param value   值
     * @param seconds 秒
     * @date 2018年10月10日
     * @author ybxxszl
     * @description TODO
     */
    public static void set(String key, String value, int seconds) {

        Jedis jedis = JedisUtil.getJedis();

        jedis.set(key, value);

        jedis.expire(key, seconds);

        jedis.close();

    }

    /**
     * @param key   键
     * @param value 值
     * @date 2018年10月10日
     * @author ybxxszl
     * @description TODO
     */
    public static void set(String key, String value) {

        Jedis jedis = JedisUtil.getJedis();

        jedis.set(key, value);

        jedis.close();

    }

    /**
     * @param key 键
     * @return String 值
     * @date 2018年10月10日
     * @author ybxxszl
     * @description TODO
     */
    public static String get(String key) {

        Jedis jedis = JedisUtil.getJedis();

        String value = jedis.get(key);

        jedis.close();

        return value;

    }

    /**
     * @param key 键
     * @date 2018年10月19日
     * @author ybxxszl
     * @description TODO
     */
    public static void del(String key) {

        Jedis jedis = JedisUtil.getJedis();

        jedis.del(key);

        jedis.close();

    }

    public static int lpush(String key, String... values) {

        Jedis jedis = JedisUtil.getJedis();

        Long num = jedis.lpush(key, values);

        jedis.close();

        return num.intValue();

    }

    public static int rpush(String key, String... values) {

        Jedis jedis = JedisUtil.getJedis();

        Long num = jedis.rpush(key, values);

        jedis.close();

        return num.intValue();

    }

    public static String lpop(String key) {

        Jedis jedis = JedisUtil.getJedis();

        String value = jedis.lpop(key);

        jedis.close();

        return value;

    }

    public static String rpop(String key) {

        Jedis jedis = JedisUtil.getJedis();

        String value = jedis.rpop(key);

        jedis.close();

        return value;

    }

    public static List<String> blpop(int timeout, String... keys) {

        Jedis jedis = JedisUtil.getJedis();

        List<String> value = jedis.blpop(timeout, keys);

        jedis.close();

        return value;

    }

    /*
     * 队列中有信息时返回，没有会一直阻塞，直至超时并返回null 支持多个队列，取出优先级高的队列中的信息
     * timeout表示阻塞限制时间，如果为0，并且队列中没有信息，一直阻塞
     */
    public static List<String> brpop(int timeout, String... keys) {

        Jedis jedis = JedisUtil.getJedis();

        List<String> value = jedis.brpop(timeout, keys);

        jedis.close();

        return value;

    }

    public static int publish(String channel, String message) {

        Jedis jedis = JedisUtil.getJedis();

        Long num = jedis.publish(channel, message);

        jedis.close();

        return num.intValue();

    }

    /*
     * JedisPubSub监听
     */
    public static void subscribe(JedisPubSubListener listener, String... channels) {

        Jedis jedis = JedisUtil.getJedis();

        jedis.subscribe(listener, channels);

        jedis.close();

    }

}

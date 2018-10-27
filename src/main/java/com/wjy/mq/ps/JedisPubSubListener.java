package com.wjy.mq.ps;

import redis.clients.jedis.JedisPubSub;

/**
 * 订阅者/发布者 监听
 */
public class JedisPubSubListener extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {

        System.out.println("频道：" + channel + " 订阅消息：" + message);

    }

}

package com.wjy.mq.ps;

import com.wjy.jedis.RedisUtil;

public class Publisher {

    public static int publish(String channel, String message) {

        int num = RedisUtil.publish(channel, message);

        return num;

    }

}

package com.wjy.mq.redismq.pc;

import com.wjy.jedis.RedisUtil;

public class Pusher {

	public static int push(String key, String message) {

		int num = RedisUtil.lpush(key, message);

		return num;

	}

}

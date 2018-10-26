package com.wjy.mq.redismq.pc;

import java.util.List;

import com.wjy.jedis.RedisUtil;

public class Poper {

	public static String pop(int timeout, String... keys) {

		List<String> list = RedisUtil.brpop(timeout, keys);

		return list.get(1);

	}

}

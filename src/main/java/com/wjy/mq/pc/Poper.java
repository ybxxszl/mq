package com.wjy.mq.pc;

import com.wjy.jedis.RedisUtil;

import java.util.List;

public class Poper {

    public static String pop(int timeout, String... keys) {

        List<String> list = RedisUtil.brpop(timeout, keys);

        return list.get(1);

    }

}

package com.wjy.test;

import com.rabbitmq.client.Channel;
import com.wjy.util.ChannelUtil;

/*
 * https://blog.csdn.net/robertohuang
 */
public class test {

	public static void main(String[] args) {

		Channel channel = ChannelUtil.getChannel("测试");

		System.out.println(channel.toString());

	}

}

package com.wjy.mq.redismq.ps;

import redis.clients.jedis.JedisPubSub;

public class JedisPubSubListener extends JedisPubSub {

	@Override
	public void onMessage(String channel, String message) {

		System.out.println("onMessage取得订阅后的处理");

		System.out.println("频道：" + channel + " 订阅消息：" + message);

	}

	@Override
	public void onSubscribe(String channel, int subscribedChannels) {

		System.out.println("onSubscribe确定订阅时的处理");

		System.out.println("频道：" + channel + " 订阅频道序号：" + subscribedChannels);

	}

	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {

		System.out.println("onUnsubscribe取消订阅时的处理");

		System.out.println("频道：" + channel + " 订阅频道序号：" + subscribedChannels);

	}

	// @Override
	// public void onPMessage(String pattern, String channel, String message) {
	//
	// System.out.println("onPMessage取得表达式订阅后的处理");
	//
	// }
	//
	// @Override
	// public void onPSubscribe(String pattern, int subscribedChannels) {
	//
	// System.out.println("onPSubscribe确定表达式订阅时的处理");
	//
	// }
	//
	// @Override
	// public void onPUnsubscribe(String pattern, int subscribedChannels) {
	//
	// System.out.println("onPUnsubscribe取消表达式订阅时的处理");
	//
	// }

}

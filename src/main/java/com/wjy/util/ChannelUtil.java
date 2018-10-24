package com.wjy.util;

import java.util.HashMap;
import java.util.Map;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ChannelUtil {

	private static ConnectionFactory connectionFactory = null;

	private static Connection connection = null;

	private static Channel channel = null;

	static {

		connectionFactory = new ConnectionFactory();

		// 配置连接信息
		connectionFactory.setHost("127.0.0.1");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");

		// 网络异常，每10秒重试
		connectionFactory.setAutomaticRecoveryEnabled(true);
		connectionFactory.setNetworkRecoveryInterval(10000);

		// 设置属性
		Map<String, Object> clientProperties = new HashMap<String, Object>();
		clientProperties.put("message", "消息队列 - 邮件");
		connectionFactory.setClientProperties(clientProperties);

	}

	public static Channel getChannel(String connectionName) {

		try {

			connection = connectionFactory.newConnection(connectionName);

			channel = connection.createChannel();

		} catch (Exception e) {

			System.out.println("获取Rabbit连接失败！！！");

			e.printStackTrace();

		}

		return channel;

	}

}

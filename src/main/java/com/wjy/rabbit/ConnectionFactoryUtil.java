package com.wjy.rabbit;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.wjy.util.PropertiesUtil;

public class ConnectionFactoryUtil {

	private static String host = null;
	private static String port = null;
	private static String username = null;
	private static String password = null;

	private static ConnectionFactory connectionFactory = null;

	static {

		host = PropertiesUtil.getValue("rabbit.host");
		port = PropertiesUtil.getValue("rabbit.port");
		username = PropertiesUtil.getValue("rabbit.username");
		password = PropertiesUtil.getValue("rabbit.password");

		connectionFactory = new ConnectionFactory();

		connectionFactory.setHost(host);
		connectionFactory.setPort(Integer.parseInt(port));
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);

	}

	public static Connection getConnection() {

		Connection connection = null;

		try {

			connection = connectionFactory.newConnection();

		} catch (Exception e) {

			System.out.println("Rabbit连接获取失败！！！");
			e.printStackTrace();

		}

		return connection;

	}

}

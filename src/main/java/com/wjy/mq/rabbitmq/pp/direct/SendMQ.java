package com.wjy.mq.rabbitmq.pp.direct;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wjy.mq.rabbitmq.MQInfo;
import com.wjy.rabbit.ConnectionFactoryUtil;

public class SendMQ {

	@Test
	public void test() throws IOException, TimeoutException {

		Connection connection = ConnectionFactoryUtil.getConnection();
		Channel channel = connection.createChannel();

		/*
		 * 声明队列：发布者/订阅者
		 * 
		 * @exchange 交换机名称
		 * 
		 * @type 交换机类型：direct（分发：将消息推送给所有路由键完全匹配的已订阅的订阅者）
		 * 
		 * @durable 是否持久化，即服务器重启时生存
		 * 
		 * @autoDelete 所有订阅者客户端断开后是否自动删除
		 * 
		 * @arguments 其他属性
		 */
		channel.exchangeDeclare(MQInfo.getDirectExchangeName(), "direct", false, false, null);

		String routingKey;

		int n;

		for (int i = 1; i <= 20; i++) {

			n = (int) (Math.random() * 5 + 1);

			if (n == 1) {

				routingKey = MQInfo.getRoutingkey()[1];

			} else {

				routingKey = MQInfo.getRoutingkey()[0];

			}

			/*
			 * 发送消息
			 * 
			 * @exchange 交换机名称
			 * 
			 * @routingKey 路由键
			 * 
			 * @props 其他属性
			 * 
			 * @body 消息
			 */
			channel.basicPublish(MQInfo.getDirectExchangeName(), routingKey, null, (routingKey + i).getBytes());

			System.out.println("第" + i + "条消息发送完成");

			try {

				Thread.sleep(1000);

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

		}

		channel.close();
		connection.close();

	}

}

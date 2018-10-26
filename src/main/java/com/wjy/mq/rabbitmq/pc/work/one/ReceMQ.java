package com.wjy.mq.rabbitmq.pc.work.one;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wjy.mq.rabbitmq.MQInfo;
import com.wjy.rabbit.ConnectionFactoryUtil;

public class ReceMQ {

	public static void main(String[] args) throws IOException, TimeoutException {

		System.out.println("工作1开始");

		Connection connection = ConnectionFactoryUtil.getConnection();
		Channel channel = connection.createChannel();

		/*
		 * 声明队列：生产者/消费者
		 * 
		 * @queue 队列名称
		 * 
		 * @durable 是否持久化，即服务器重启时生存
		 * 
		 * @exclusive 独占队列，创建者可以使用的私有队列，断开后自动删除
		 * 
		 * @autoDelete 所有消费者客户端断开后是否自动删除
		 * 
		 * @arguments 其他属性
		 */
		channel.queueDeclare(MQInfo.getQueueName(), false, false, false, null);

		/*
		 * @prefetchCount 每次从队列中接收的消息数量
		 */
		channel.basicQos(1);

		ReceConsumer consumer = new ReceConsumer(channel);

		/*
		 * 消息确认机制
		 * 
		 * @queue 队列名称
		 * 
		 * @autoAck 是否自动Ack
		 * 
		 * @callback 消息处理
		 */
		channel.basicConsume(MQInfo.getQueueName(), false, consumer);

	}

}

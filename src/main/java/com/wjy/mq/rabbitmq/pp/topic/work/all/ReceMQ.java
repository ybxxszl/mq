package com.wjy.mq.rabbitmq.pp.topic.work.all;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wjy.mq.rabbitmq.MQInfo;
import com.wjy.rabbit.ConnectionFactoryUtil;

public class ReceMQ {

	public static void main(String[] args) throws IOException, TimeoutException {

		System.out.println("工作ALL开始");

		Connection connection = ConnectionFactoryUtil.getConnection();
		Channel channel = connection.createChannel();

		/*
		 * 声明队列：发布者/订阅者
		 * 
		 * @exchange 交换机名称
		 * 
		 * @type 交换机类型：topic（分发：将消息推送给所有路由键模糊匹配的已订阅的订阅者）
		 * 
		 * @durable 是否持久化，即服务器重启时生存
		 * 
		 * @autoDelete 所有订阅者客户端断开后是否自动删除
		 * 
		 * @arguments 其他属性
		 */
		channel.exchangeDeclare(MQInfo.getTopicExchangeName(), "topic", false, false, null);

		// 获取随机的队列名称
		String queue = channel.queueDeclare().getQueue();

		/*
		 * @queue 队列名称
		 * 
		 * @exchange 交换机名称
		 * 
		 * @routingKey 路由键（“*”匹配一个单词）
		 * 
		 * @arguments 其他属性
		 */
		channel.queueBind(queue, MQInfo.getTopicExchangeName(), "log.grade.*", null);

		ReceConsumer consumer = new ReceConsumer(channel);

		// 消息确认机制
		channel.basicConsume(queue, true, consumer); // 随机的队列自动删除，没有订阅者订阅或者已订阅者取消订阅

	}

}

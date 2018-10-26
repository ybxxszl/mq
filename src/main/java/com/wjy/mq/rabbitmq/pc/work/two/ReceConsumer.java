package com.wjy.mq.rabbitmq.pc.work.two;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class ReceConsumer extends DefaultConsumer {

	private final Channel channel;

	public ReceConsumer(Channel channel) {
		super(channel);
		this.channel = channel;
	}

	@Override
	public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
			throws IOException {

		try {

			Thread.sleep(3000);

		} catch (InterruptedException e) {

			e.printStackTrace();

		}

		/*
		 * Ack消息
		 * 
		 * @deliveryTag 消息的index
		 * 
		 * @multiple 是否批量处理，即一次性Ack所有小于deliveryTag的消息
		 */
		channel.basicAck(envelope.getDeliveryTag(), false);

		/*
		 * Nack消息
		 * 
		 * @deliveryTag 消息的index
		 * 
		 * @multiple 是否批量处理，即一次性Ack所有小于deliveryTag的消息
		 * 
		 * @requeue 被拒绝的消息是否重新加入队列
		 */
		// channel.basicNack(envelope.getDeliveryTag(), false, true);

		System.out.println("message: " + new String(body));

	}

}

package com.wjy.mq.rabbitmq;

/*
 * 定义消息类型
 * 生产者/消费者：使用队列名称实现
 * 发布者/订阅者：使用交换机名称实现
 * 注意：不同的消息队列使用不同的名称
 */
public class MQInfo {

	private static final String QUEUE_NAME = "TestQueue";

	private static final String FANOUT_EXCHANGE_NAME = "FANOUT_EXCHANGE";
	private static final String DIRECT_EXCHANGE_NAME = "DIRECT_EXCHANGE";
	private static final String TOPIC_EXCHANGE_NAME = "TOPIC_EXCHANGE";

	public static String getQueueName() {
		return QUEUE_NAME;
	}

	public static String getFanoutExchangeName() {
		return FANOUT_EXCHANGE_NAME;
	}

	public static String getDirectExchangeName() {
		return DIRECT_EXCHANGE_NAME;
	}

	public static String getTopicExchangeName() {
		return TOPIC_EXCHANGE_NAME;
	}

	/*
	 * 定义路由键名称 发布者/订阅者使用路由键判断是否推送
	 */
	private static final String[] ROUTINGKEY = { "log.grade.info", "log.grade.error" };

	public static String[] getRoutingkey() {
		return ROUTINGKEY;
	}

}

package com.wjy.mq.pc;

import com.alibaba.fastjson.JSONObject;
import com.wjy.util.MailUtil;
import com.wjy.util.PropertiesUtil;

/**
 * 生产者/消费者 监听
 */
public class JedisPusPopListener {

	private static String fromAddress;
	private static String account;
	private static String code;

	static {

		fromAddress = PropertiesUtil.getValue("mail.fromAddress");
		account = PropertiesUtil.getValue("mail.account");
		code = PropertiesUtil.getValue("mail.code");

	}

	public void onMessage(String... keys) {

		while (true) {

			String message = Poper.pop(0, keys);

			JSONObject object = JSONObject.parseObject(message);

			String text = object.getString("text");
			String recipientAddress = object.getString("recipientAddress");

			boolean flag = MailUtil.sendMail("验证码", text, new String[] {}, recipientAddress, fromAddress, account,
					code);

			System.out.print(recipientAddress + "：" + text);

			if (flag) {

				System.out.println(" --> 邮件发送成功！！！");

			} else {

				System.out.println(" --> 邮件发送失败！！！");

			}

		}

	}

}

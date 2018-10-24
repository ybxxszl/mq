package com.wjy.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * 邮件工具类
 */
public class MailUtil {

	/**
	 * 发送邮件
	 * 
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 * @param recipientAddress
	 *            收件人地址
	 * @param fromAddress
	 *            发件人地址
	 * @param account
	 *            账号
	 * @param code
	 *            授权码
	 * @param host
	 *            邮件服务器
	 * @return 发送结果
	 */
	public static boolean sendMail(String subject, String content, String recipientAddress, String fromAddress,
			String account, String code, String host) {

		try {

			// 获取配置
			Properties props = getProperties(host);

			// 获取Session
			Session session = Session.getInstance(props);

			// 获取Transport
			Transport transport = session.getTransport();

			// 连接邮件服务器（邮件服务器，账号，授权码）
			transport.connect(host, account, code);

			// 创建邮件
			Message message = createMail(subject, content, recipientAddress, fromAddress, session);

			// 发送邮件
			transport.sendMessage(message, message.getAllRecipients());

			// 关闭连接
			transport.close();

		} catch (Exception e) {

			System.out.println("邮件发送失败！！！");

			e.printStackTrace();

			return false;

		}

		return true;

	}

	private static Properties getProperties(String host) throws GeneralSecurityException {

		Properties props = new Properties();

		// 邮件服务器
		props.setProperty("mail.host", host);

		// 身份验证
		props.setProperty("mail.smtp.auth", "true");

		// 协议名称
		props.setProperty("mail.transport.protocol", "smtp");

		return props;

	}

	public static MimeMessage createMail(String subject, String content, String recipientAddress, String fromAddress,
			Session session) throws AddressException, MessagingException, FileNotFoundException, IOException {

		MimeMessage mimeMessage = new MimeMessage(session);

		// 邮件的发件人
		mimeMessage.setFrom(new InternetAddress(fromAddress));

		// 邮件的收件人
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientAddress));

		// 邮件的标题
		mimeMessage.setSubject(subject);

		// 邮件的内容
		mimeMessage.setContent(content, "text/html;charset=UTF-8");

		return mimeMessage;

	}

	public static void main(String[] args) {

		boolean flag = sendMail("测试主题", "测试内容", "1062837400@qq.com", "1062837400@qq.com", "1062837400",
				"bddomwnwxqlmbcef", "smtp.qq.com");

		if (flag) {

			System.out.println("发送成功");

		} else {

			System.out.println("发送失败");

		}

	}

}

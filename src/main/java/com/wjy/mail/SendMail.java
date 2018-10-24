package com.wjy.mail;

import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {

	public static void main(String[] args) throws Exception {

		Properties prop = new Properties();
		// 开启DEBUG调试，控制台查看信息
		prop.setProperty("mail.debug", "true");
		// 设置邮件服务器
		prop.setProperty("mail.host", "smtp.qq.com");
		// 身份验证
		prop.setProperty("mail.smtp.auth", "true");
		// 协议名称
		prop.setProperty("mail.transport.protocol", "smtp");

		// 创建Session
		Session session = Session.getInstance(prop);
		// 获取Transport
		Transport transport = session.getTransport();
		// 连接邮件服务器（邮件服务器、帐号、授权码）
		transport.connect("smtp.qq.com", "1062837400", "bddomwnwxqlmbcef");
		// 创建邮件
		Message simpleMail = createSimpleMail(session);
		Message imageMail = createImageMail(session);
		Message attachMail = createAttachMail(session);
		// 发送邮件
		transport.sendMessage(simpleMail, simpleMail.getAllRecipients());
		transport.sendMessage(imageMail, imageMail.getAllRecipients());
		transport.sendMessage(attachMail, attachMail.getAllRecipients());
		// 连接关闭
		transport.close();

	}

	public static MimeMessage createSimpleMail(Session session) throws Exception {

		// 创建邮件
		MimeMessage mimeMessage = new MimeMessage(session);
		// 邮件的发件人
		mimeMessage.setFrom(new InternetAddress("1062837400@qq.com"));
		// 邮件的收件人
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("1062837400@qq.com"));
		// 邮件的标题
		mimeMessage.setSubject("JavaMail");
		// 邮件的内容
		mimeMessage.setContent("Hello World !!!", "text/html;charset=UTF-8");
		// 返回
		return mimeMessage;

	}

	public static MimeMessage createImageMail(Session session) throws Exception {

		// 创建邮件
		MimeMessage mimeMessage = new MimeMessage(session);
		// 邮件的基本信息
		// 邮件的发件人
		mimeMessage.setFrom(new InternetAddress("1062837400@qq.com"));
		// 邮件的收件人
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("1062837400@qq.com"));
		// 邮件的标题
		mimeMessage.setSubject("JavaMail-Image");
		// 邮件的正文
		// 准备邮件正文数据
		MimeBodyPart text = new MimeBodyPart();
		text.setContent("Hello World !!!<img src='cid:xxx.jpg'>", "text/html;charset=UTF-8");
		// 准备邮件图片数据
		MimeBodyPart image = new MimeBodyPart();
		DataHandler dh = new DataHandler(new FileDataSource("D:\\maomao.jpg"));
		image.setDataHandler(dh);
		image.setContentID("xxx.jpg");
		// 创建容器描述数据关系
		MimeMultipart mm = new MimeMultipart();
		mm.addBodyPart(text);
		mm.addBodyPart(image);
		mm.setSubType("related");
		mimeMessage.setContent(mm);
		mimeMessage.saveChanges();
		// 保存到本地
		mimeMessage.writeTo(new FileOutputStream("D:\\ImageMail.eml"));
		// 返回
		return mimeMessage;

	}

	public static MimeMessage createAttachMail(Session session) throws Exception {

		// 创建邮件
		MimeMessage mimeMessage = new MimeMessage(session);
		// 邮件的基本信息
		// 邮件的发件人
		mimeMessage.setFrom(new InternetAddress("1062837400@qq.com"));
		// 邮件的收件人
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("1062837400@qq.com"));
		// 邮件的标题
		mimeMessage.setSubject("JavaMail-Attach");
		// 准备邮件正文数据
		MimeBodyPart text = new MimeBodyPart();
		text.setContent("Hello World !!!", "text/html;charset=UTF-8");
		// 准备邮件附件数据
		MimeBodyPart attach = new MimeBodyPart();
		DataHandler dh = new DataHandler(new FileDataSource("D:\\maomao.jpg"));
		attach.setDataHandler(dh);
		attach.setFileName(dh.getName());
		// 创建容器描述数据关系
		MimeMultipart mm = new MimeMultipart();
		mm.addBodyPart(text);
		mm.addBodyPart(attach);
		mm.setSubType("mixed");
		mimeMessage.setContent(mm);
		mimeMessage.saveChanges();
		// 保存到本地
		mimeMessage.writeTo(new FileOutputStream("D:\\attachMail.eml"));
		// 返回
		return mimeMessage;

	}

}

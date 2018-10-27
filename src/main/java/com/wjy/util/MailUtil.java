package com.wjy.util;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;

/*
 * 邮件工具类
 */
public class MailUtil {

    /**
     * 发送邮件
     *
     * @param subject          邮件标题
     * @param text             邮件正文
     * @param attachs          邮件附件
     * @param recipientAddress 收件人地址
     * @param fromAddress      发件人地址
     * @param account          账号
     * @param code             授权码
     * @return 发送结果
     */
    public static boolean sendMail(String subject, String text, String[] attachs, String recipientAddress,
                                   String fromAddress, String account, String code) {

        String host = "smtp." + recipientAddress.split("@")[1].split(".")[0] + ".com";

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
            Message message = createMail(subject, text, attachs, recipientAddress, fromAddress, session);

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

    /**
     * 创建邮件
     *
     * @param subject          邮件标题
     * @param text             邮件正文
     * @param attachs          邮件附件
     * @param recipientAddress 收件人地址
     * @param fromAddress      发件人地址
     * @param session          Session
     * @return 邮件
     * @throws AddressException
     * @throws MessagingException
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static MimeMessage createMail(String subject, String text, String[] attachs, String recipientAddress,
                                          String fromAddress, Session session)
            throws AddressException, MessagingException, FileNotFoundException, IOException {

        MimeMessage mimeMessage = new MimeMessage(session);

        // 邮件的发件人
        mimeMessage.setFrom(new InternetAddress(fromAddress));

        // 邮件的收件人
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientAddress));

        // 邮件的标题
        mimeMessage.setSubject(subject);

        // 设置正文与附件的关系
        MimeMultipart textAndAttach = new MimeMultipart();

        // 邮件的正文
        MimeBodyPart bodyText = new MimeBodyPart();
        bodyText.setContent(text, "text/html;charset=UTF-8");
        textAndAttach.addBodyPart(bodyText);

        // 邮件的附件
        DataHandler handlerAttach = null;
        for (int i = 0; i < attachs.length; i++) {
            handlerAttach = new DataHandler(new FileDataSource(attachs[i]));
            MimeBodyPart bodyAttach = new MimeBodyPart();
            bodyAttach.setDataHandler(handlerAttach);
            bodyAttach.setFileName(MimeUtility.encodeText(handlerAttach.getName()));
            textAndAttach.addBodyPart(bodyAttach);
        }

        textAndAttach.setSubType("mixed");

        mimeMessage.setContent(textAndAttach);

        mimeMessage.saveChanges();

        // 保存到本地
        mimeMessage.writeTo(new FileOutputStream("D:\\JavaMail.eml"));

        // 返回邮件
        return mimeMessage;

    }

    /**
     * 获取配置
     *
     * @param host 邮件服务器
     * @return 配置
     * @throws GeneralSecurityException
     */
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

    public static void main(String[] args) {

        String text = "猫";
        String[] attachs = {};

        boolean f = sendMail("猫", text, attachs, "1062837400@qq.com", "1062837400@qq.com", "1062837400",
                "bddomwnwxqlmbcef");

        if (f) {

            System.out.println("发送成功");

        } else {

            System.out.println("发送失败");

        }

    }

}

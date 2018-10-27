package com.wjy.mq.pc;

import com.wjy.bean.MailInfo;
import com.wjy.util.JSONUtil;
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

            MailInfo mailInfo = JSONUtil.jsonToPojo(message, MailInfo.class);

            boolean flag = MailUtil.sendMail("验证码", mailInfo.getText(), new String[]{}, mailInfo.getRecipientAddress(), fromAddress, account, code);

            System.out.print(mailInfo.getRecipientAddress() + "：" + mailInfo.getText());

            if (flag) {

                System.out.println(" --> 邮件发送成功！！！");

            } else {

                System.out.println(" --> 邮件发送失败！！！");

            }

        }

    }

}

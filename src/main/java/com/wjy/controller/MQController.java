package com.wjy.controller;

import com.wjy.bean.MQInfo;
import com.wjy.bean.MailInfo;
import com.wjy.bean.SMSInfo;
import com.wjy.mq.pc.Pusher;
import com.wjy.mq.ps.Publisher;
import com.wjy.result.JSONResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date 2018/10/27
 * @Author ybxxszl
 * @Desc
 **/
@RestController
public class MQController {

    @PostMapping(value = "/postMsg")
    public JSONResult postMsg(MQInfo mqInfo) {

        String type = mqInfo.getType();
        String name = mqInfo.getName();

        MailInfo mailInfo = mqInfo.getMailInfo();
        SMSInfo smsInfo = mqInfo.getSMSInfo();

        int num = 0;

        try {

            if ("pc".equals(type)) {

                if (name.contains("Mail")) {

                    num = Pusher.push(name, mailInfo.toString());

                }

                if (name.contains("SMS")) {

                    num = Pusher.push(name, smsInfo.toString());

                }

            }

            if ("ps".equals(type)) {

                if (name.contains("Mail")) {

                    num = Publisher.publish(name, mailInfo.toString());

                }

                if (name.contains("SMS")) {

                    num = Publisher.publish(name, smsInfo.toString());

                }

            }

            return JSONResult.ok(num);

        } catch (Exception e) {

            return JSONResult.errorException(e.getMessage());

        }

    }

}

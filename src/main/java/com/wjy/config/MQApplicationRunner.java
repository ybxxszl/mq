package com.wjy.config;

import com.wjy.mq.pc.JedisPusPopListener;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Date 2018/10/27
 * @Author ybxxszl
 * @Desc 项目启动时执行
 **/
@Component
public class MQApplicationRunner implements ApplicationRunner {

    private JedisPusPopListener jedisPusPopListener = new JedisPusPopListener();

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

        System.out.println("JedisPusPopListener的onMessage正在执行");

        jedisPusPopListener.onMessage("Mail_VerifyCode");

    }

}

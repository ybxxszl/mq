package com.wjy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wjy.mq.pc.Pusher;
import com.wjy.mq.ps.Publisher;
import com.wjy.result.JSONResult;
import com.wjy.util.HttpClientUtil;

/**
 * @Date 2018/10/27
 * @Author ybxxszl
 * @Desc
 **/
@RestController
public class MQController {

	@PostMapping(value = "/postVerifyCode")
	public JSONResult postMsg(HttpServletRequest request) {

		int num = 0;

		try {

			JSONObject object = HttpClientUtil.getJSON(request);

			String type = object.getString("type");
			String name = object.getString("name");
			JSONObject mailInfo = object.getJSONObject("mailInfo");
			JSONObject smsInfo = object.getJSONObject("smsInfo");

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

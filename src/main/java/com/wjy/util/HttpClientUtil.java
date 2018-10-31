package com.wjy.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpClientUtil {

	private static PoolingHttpClientConnectionManager pool = new PoolingHttpClientConnectionManager();

	static {

		pool.setMaxTotal(200); // 目标主机的最大连接数
		pool.setDefaultMaxPerRoute(20); // 每个路由的基础连接数

	}

	/**
	 * 高并发请求连接池
	 * 
	 * @param uri
	 * @param header
	 * @return
	 * @throws Exception
	 */
	public static JSONObject doGetPool(String uri, Map<String, String> header) throws Exception {
		CloseableHttpClient client = HttpClients.custom().setConnectionManager(pool).build();
		HttpGet get = new HttpGet(uri);
		if (header != null) {
			for (Map.Entry<String, String> entry : header.entrySet()) {
				get.setHeader(entry.getKey(), entry.getValue());
			}
		}
		HttpResponse response = client.execute(get);
		int code = response.getStatusLine().getStatusCode();
		String data = EntityUtils.toString(response.getEntity());
		JSONObject object = new JSONObject();
		object.put("code", code);
		object.put("data", data);
		return object;
	}

	/**
	 * 高并发请求连接池
	 * 
	 * @param uri
	 * @param body
	 * @param header
	 * @return
	 * @throws Exception
	 */
	public static JSONObject doPostPool(String uri, JSONObject body, Map<String, String> header) throws Exception {
		CloseableHttpClient client = HttpClients.custom().setConnectionManager(pool).build();
		HttpPost post = new HttpPost(uri);
		if (header != null) {
			for (Map.Entry<String, String> entry : header.entrySet()) {
				post.setHeader(entry.getKey(), entry.getValue());
			}
		}
		StringEntity entity = new StringEntity(body == null ? "" : body.toString(), "UTF-8");
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		post.setEntity(entity);
		HttpResponse response = client.execute(post);
		int code = response.getStatusLine().getStatusCode();
		String data = EntityUtils.toString(response.getEntity());
		JSONObject object = new JSONObject();
		object.put("code", code);
		object.put("data", data);
		return object;
	}

	/**
	 * GET请求：参数在URL中
	 * 
	 * @param uri
	 * @param header
	 * @return
	 * @throws Exception
	 */
	public static JSONObject doGet(String uri, Map<String, String> header) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(uri);
		if (header != null) {
			for (Map.Entry<String, String> entry : header.entrySet()) {
				get.setHeader(entry.getKey(), entry.getValue());
			}
		}
		HttpResponse response = client.execute(get);
		int code = response.getStatusLine().getStatusCode();
		String data = EntityUtils.toString(response.getEntity());
		client.close();
		JSONObject object = new JSONObject();
		object.put("code", code);
		object.put("data", data);
		return object;
	}

	/**
	 * POST请求：参数不在URL中
	 * 
	 * @param uri
	 * @param body
	 * @param header
	 * @return
	 * @throws Exception
	 */
	public static JSONObject doPost(String uri, JSONObject body, Map<String, String> header) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(uri);
		if (header != null) {
			for (Map.Entry<String, String> entry : header.entrySet()) {
				post.setHeader(entry.getKey(), entry.getValue());
			}
		}
		StringEntity entity = new StringEntity(body == null ? "" : body.toString(), "UTF-8");
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		post.setEntity(entity);
		HttpResponse response = client.execute(post);
		int code = response.getStatusLine().getStatusCode();
		String data = EntityUtils.toString(response.getEntity());
		client.close();
		JSONObject object = new JSONObject();
		object.put("code", code);
		object.put("data", data);
		return object;
	}

	/**
	 * 获取POST请求的参数
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static JSONObject getJSON(HttpServletRequest request) throws Exception {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		String temp;
		while ((temp = br.readLine()) != null) {
			sb.append(temp);
		}
		br.close();
		return JSONObject.parseObject(sb.toString());
	}

}

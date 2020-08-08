package com.tom.connectpool;

import cn.hutool.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * @author WangTao
 */
public class HttpPoolStudy {
    private static final Logger logger = LoggerFactory.getLogger(HttpPoolStudy.class);

    private static CloseableHttpClient httpClient = null;
    private static final String  URL = "http://www.imooc.com/user/login";

    static {
        httpClient = HttpClients.custom().setMaxConnPerRoute(1)
                .setMaxConnTotal(10)
                .evictIdleConnections(60, TimeUnit.SECONDS).build();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                httpClient.close();
                logger.info("关闭http连接");
            } catch (IOException ignored) {
            }
        }));
    }

    public static String right(){
        try (CloseableHttpResponse response =
                     httpClient.execute(new HttpGet(URL))) {
            return EntityUtils.toString(response.getEntity());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public static String post(String url, Map<String,String> parameters){
        UrlEncodedFormEntity entity = null;
        CloseableHttpResponse httpResponse = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> tmp = new ArrayList<>();
            parameters.entrySet().forEach(v -> {
                tmp.add( new BasicNameValuePair(v.getKey(),v.getValue()));
            });
            logger.info("temp-->size{}",tmp.size());
            entity = new UrlEncodedFormEntity(tmp,"utf-8");
            httpPost.setEntity(entity);
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity= httpResponse.getEntity();
            String result = EntityUtils.toString(httpEntity);
            JSONObject jsonObject = new JSONObject(result);
            logger.info("返回结果:{}",jsonObject);
            return result;
        } catch (IOException e) {
            logger.error("请求错误:{}--->错误原因:{}",e,e.getMessage());
        }finally {
            if (httpResponse != null){
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    logger.error("请求错误:{}--->错误原因:{}",e,e.getMessage());
                }
            }
        }
        return null;
    }


    public static void main(String[] args) {
        Map<String,String> parameters = new HashMap<>(16);
        parameters.put("username","13206573871@163.com");
        parameters.put("password","123456");
        System.out.println(post(URL, parameters));

    }
}

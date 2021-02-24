package cn.graydove.httpmaster.core;

import cn.graydove.httpmaster.core.common.HeaderConstant;
import cn.graydove.httpmaster.core.request.HttpRequest;
import cn.graydove.httpmaster.core.request.HttpRequestFactory;
import cn.graydove.httpmaster.core.request.support.DefaultHttpRequestFactory;
import cn.graydove.httpmaster.core.response.HttpResponse;
import cn.graydove.httpmaster.core.response.support.StringHttpContent;
import cn.graydove.httpmaster.core.engine.support.httpclient.HttpClientEngine;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class HttpTest {

    private HttpClientEngine engine = new HttpClientEngine();

//    @Test
    public void testRequest() {
        HttpRequest httpRequest = engine.newHttpRequest()
                .url("http://localhost:8080/login")
                .header()
                .addHeader("Connection", "keep-alive")
                .request()
                .keyValueBody()
                .addParam("username", "admin")
                .addParam("password", "123456")
                .request();
        HttpResponse response = engine.post(httpRequest);
        for (Pair<String, String> head : response.getHeads()) {
            System.out.println(head.getKey() + ":" + head.getValue());
        }
        StringHttpContent stringContent = new StringHttpContent(response.getHttpContent());

        System.out.println("------------------------------------------------------");

        JSONObject jsonObject = JSONUtil.parseObj(stringContent.getContentStr());
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            System.out.println(entry.getKey() + ": " +entry.getValue());
        }
        String token = StrUtil.toString(jsonObject.get("token"));
        String prefix = StrUtil.toString(jsonObject.get("prefix"));


        httpRequest = engine.newHttpRequest()
                .url("http://localhost:8080/admin/visit")
                .header()
                .addHeader("Authorization", prefix + " " + token)
                .request()
                .query()
                .addQuery("begin", "2021-01-24")
                .addQuery("end", "2021-01-31")
                .request();

        response = engine.get(httpRequest);
        StringHttpContent stringHttpContent = new StringHttpContent(response.getHttpContent());
        System.out.println(stringHttpContent.getContentStr());
    }

    @Test
    public void spiderTest() {
        HttpRequest request = engine.newHttpRequest()
                .url("https://www.xinxs.la/0_52/4741511.html")
                .header()
                .addHeader(HeaderConstant.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.80 Safari/537.36")
                .request();
        HttpResponse response = engine.get(request);
        String str = new StringHttpContent(response.getHttpContent()).getContentStr();
        Assert.assertNotNull(str);
    }

    @Test
    public void baiduTest() {
        HttpRequest request = engine.newHttpRequest()
                .url("https://www.baidu.com/s")
                .header()
                .addHeader(HeaderConstant.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.80 Safari/537.36")
                .request()
                .query()
                .addQuery("wd", "遮天")
                .request();
        HttpResponse response = engine.get(request);
        String str = new StringHttpContent(response.getHttpContent()).getContentStr();
        Assert.assertNotNull(str);
    }
}

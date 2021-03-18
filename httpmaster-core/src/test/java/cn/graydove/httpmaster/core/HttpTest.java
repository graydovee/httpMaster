package cn.graydove.httpmaster.core;

import cn.graydove.httpmaster.core.common.HeaderConstant;
import cn.graydove.httpmaster.core.engine.HttpEngine;
import cn.graydove.httpmaster.core.engine.support.okhttp.OkHttpEngine;
import cn.graydove.httpmaster.core.enums.HttpMethod;
import cn.graydove.httpmaster.core.request.HttpRequest;
import cn.graydove.httpmaster.core.request.HttpRequestFactory;
import cn.graydove.httpmaster.core.request.support.DefaultHttpRequestFactory;
import cn.graydove.httpmaster.core.response.HttpContent;
import cn.graydove.httpmaster.core.response.HttpResponse;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class HttpTest {

    private HttpEngine engine = new OkHttpEngine();

    private HttpRequestFactory httpRequestFactory = new DefaultHttpRequestFactory();

//    @Test
    public void testRequest() {
        HttpRequest httpRequest = httpRequestFactory.newHttpRequest()
                .url("http://localhost:8080/login")
                .header()
                .addHeader("Connection", "keep-alive")
                .build()
                .keyValueBody()
                .addParam("username", "admin")
                .addParam("password", "123456")
                .build();
        HttpResponse response = engine.post(httpRequest);
        for (Pair<String, String> head : response.getHeader()) {
            System.out.println(head.getKey() + ":" + head.getValue());
        }
        HttpContent httpContent = response.getHttpContent();

        System.out.println("------------------------------------------------------");

        JSONObject jsonObject = JSONUtil.parseObj(httpContent.getContentStr());
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            System.out.println(entry.getKey() + ": " +entry.getValue());
        }
        String token = StrUtil.toString(jsonObject.get("token"));
        String prefix = StrUtil.toString(jsonObject.get("prefix"));


        httpRequest = httpRequestFactory.newHttpRequest()
                .url("http://localhost:8080/admin/visit")
                .header()
                .addHeader("Authorization", prefix + " " + token)
                .build()
                .query()
                .addQuery("begin", "2021-01-24")
                .addQuery("end", "2021-01-31")
                .build();

        response = engine.get(httpRequest);
        httpContent = response.getHttpContent();
        System.out.println(httpContent.getContentStr());
    }

    @Test
    public void spiderTest() {
        HttpRequest request = httpRequestFactory.newHttpRequest()
                .url("https://www.xinxs.la/0_52/4741511.html")
                .header()
                .addHeader(HeaderConstant.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.80 Safari/537.36")
                .build();
        HttpResponse response = engine.get(request);
        String str = response.getHttpContent().getContentStr();
        Assert.assertNotNull(str);
    }

    @Test
    public void baiduTest() {
        HttpRequest request = httpRequestFactory.newHttpRequest()
                .url("https://www.baidu.com/s")
                .method(HttpMethod.GET)
                .header()
                .addHeader(HeaderConstant.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.80 Safari/537.36")
                .build()
                .query()
                .addQuery("wd", "遮天")
                .build();
        InputStream inputStream = null;
        try(HttpResponse response = engine.execute(request)) {
            HttpContent httpContent = response.getHttpContent();
            inputStream = httpContent.getContent();
            byte[] bytes = IoUtil.readBytes(inputStream, 30);
            String s = new String(bytes);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            IoUtil.readBytes(inputStream);
            Assert.fail();
        } catch (Throwable e) {
            System.out.println(e.getMessage());
            System.out.println("OK");
        }
    }
}

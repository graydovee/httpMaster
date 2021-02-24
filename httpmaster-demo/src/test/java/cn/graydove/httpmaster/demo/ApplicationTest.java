package cn.graydove.httpmaster.demo;

import cn.graydove.httpmaster.demo.request.DemoClient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTest {

    @Autowired
    private DemoClient demoClient;

//    @Test
    public void testNovel() {
        Assertions.assertNotNull(demoClient);
        String login = demoClient.login("admin", "123456");
        JSONObject jsonObject = JSON.parseObject(login);
        String token = jsonObject.get("prefix") + " " + jsonObject.get("token");
        System.out.println(token);

        login = demoClient.login2("admin", "123456");
        jsonObject = JSON.parseObject(login);
        token = jsonObject.get("prefix") + " " + jsonObject.get("token");
        System.out.println(token);

        String visit = demoClient.visit(token, "2021-01-24", "2021-01-31");
        System.out.println(visit);
    }

    private String getToken(String s) {
        JSONObject jsonObject = JSON.parseObject(s);
        String token = jsonObject.get("prefix") + " " + jsonObject.get("token");
        System.out.println(token);
        return token;
    }
}
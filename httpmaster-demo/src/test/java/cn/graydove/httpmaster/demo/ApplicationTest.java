package cn.graydove.httpmaster.demo;

import cn.graydove.httpmaster.demo.model.Response;
import cn.graydove.httpmaster.demo.model.User;
import cn.graydove.httpmaster.demo.request.DemoService;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTest {

    @Autowired
    private DemoService demoService;

    @Test
    void testGetBlog() {
        String blog = demoService.blog();
        Assertions.assertNotNull(blog);
    }

    @Test
    void testGet() {
        String blog = demoService.get("https://blog.graydove.cn/");
        Assertions.assertNotNull(blog);
    }

    @Test
    void test() {
        Response<User> response = demoService.user();
        Assertions.assertNotNull(response.getData());
        User user = response.getData();
        Assertions.assertTrue(StrUtil.isNotBlank(user.getUsername()));
        Assertions.assertTrue(StrUtil.isNotBlank(user.getPassword()));
    }
}

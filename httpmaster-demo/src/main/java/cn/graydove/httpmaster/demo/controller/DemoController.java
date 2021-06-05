package cn.graydove.httpmaster.demo.controller;

import cn.graydove.httpmaster.demo.model.Response;
import cn.graydove.httpmaster.demo.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author graydove
 */
@RestController
public class DemoController {

    @GetMapping("user")
    public Response<User> user() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        return Response.success(user);
    }
}

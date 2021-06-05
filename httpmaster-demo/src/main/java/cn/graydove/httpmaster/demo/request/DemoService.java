package cn.graydove.httpmaster.demo.request;

import cn.graydove.httpmaster.core.common.HeaderConstant;
import cn.graydove.httpmaster.demo.model.Response;
import cn.graydove.httpmaster.demo.model.User;
import cn.graydove.httpmaster.starter.annotation.*;
import cn.graydove.httpmaster.starter.annotation.method.HttpGet;
import cn.graydove.httpmaster.starter.annotation.method.HttpPost;

/**
 * @author graydove
 */
@HttpService(url = "http://localhost:8853")
public interface DemoService {

    @HttpGet(path = "user")
    Response<User> user();

    @HttpGet(url = "https://blog.graydove.cn/")
    String blog();

    @HttpGet
    String get(@Url String url);
}

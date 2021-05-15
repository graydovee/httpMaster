package cn.graydove.httpmaster.demo.request;

import cn.graydove.httpmaster.core.common.HeaderConstant;
import cn.graydove.httpmaster.starter.annotation.*;
import cn.graydove.httpmaster.starter.annotation.method.HttpGet;
import cn.graydove.httpmaster.starter.annotation.method.HttpPost;

/**
 * @author graydove
 */
@HttpService(url = "http://localhost:8080")
public interface DemoService {

    @HttpPost(path = "login")
    String login(@Body String username, @Body String password);

    @HttpPost(path = "login")
    String login2(String username, String password);

    @HttpGet(path = "admin/visit")
    String visit(@Header(HeaderConstant.AUTHORIZATION) String head, @Query("begin") String arg0, @Query("end")String arg1);

    @HttpGet(url = "https://blog.graydove.cn/")
    String blog();

    @HttpGet
    String get(@Url String url);
}

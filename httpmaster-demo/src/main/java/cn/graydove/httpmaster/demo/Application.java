package cn.graydove.httpmaster.demo;

import cn.graydove.httpmaster.starter.config.EnableHttpMaster;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableHttpMaster
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}

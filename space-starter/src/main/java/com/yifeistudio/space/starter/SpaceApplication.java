package com.yifeistudio.space.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Space Application
 *
 * @author : hongyi
 * created at 2023/1/4 - 11:20
 **/
public class SpaceApplication {

    public static ConfigurableApplicationContext run(Class<?> clz, String[] args) {
        // do something...
        return SpringApplication.run(clz, args);
    }

}

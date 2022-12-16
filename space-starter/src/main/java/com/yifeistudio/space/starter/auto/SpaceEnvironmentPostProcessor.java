package com.yifeistudio.space.starter.auto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

/**
 * File Description
 *
 * @author : hongyi
 * created at 2022/12/15 - 20:05
 **/
@Configuration
class SpaceEnvironmentPostProcessor implements EnvironmentPostProcessor {


    /**
     * Post-process the given {@code environment}.
     *
     * @param environment the environment to post-process
     * @param application the application to which the environment belongs
     */
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        Map<String, Object> systemEnvironment = environment.getSystemEnvironment();
        systemEnvironment.put("spring.banner.location", "space-banner.txt");

        System.out.println("这里执行了。。。。");
    }
}

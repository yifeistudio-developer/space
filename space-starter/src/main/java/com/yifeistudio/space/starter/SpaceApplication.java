package com.yifeistudio.space.starter;

import org.springframework.boot.Banner;
import org.springframework.boot.ResourceBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Space Application
 *
 * @author : hongyi
 * created at 2023/1/4 - 11:20
 **/
public class SpaceApplication {

    public static ConfigurableApplicationContext run(Class<?> clz, String[] args) {
        SpringApplication springApplication = new SpringApplication(clz);
        Banner banner = new ResourceBanner(new ClassPathResource("yi-banner.txt"));
        springApplication.setBanner(banner);
        return springApplication.run(args);
    }

}

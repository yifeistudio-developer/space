package com.yifeistudio.space.starter;

import org.springframework.boot.Banner;
import org.springframework.boot.ResourceBanner;
import org.springframework.boot.builder.SpringApplicationBuilder;
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
        Banner banner = new ResourceBanner(new ClassPathResource("yi-banner.txt"));
        return new SpringApplicationBuilder()
                .sources(clz)
                .banner(banner)
                .run();
    }

}

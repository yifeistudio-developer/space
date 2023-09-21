package com.yifeistudio.space.starter.auto;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

/**
 * Space listener
 *
 * @author : hongyi
 * created at 2022/4/20 - 11:06
 **/
// FIXME: 2023/9/21 adjust
@Slf4j
public class SpaceListener implements ApplicationListener<ApplicationEvent> {


    @Resource
    private Environment environment;

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(@NotNull ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            log.info("space-starter is testing...");
        }
    }
}

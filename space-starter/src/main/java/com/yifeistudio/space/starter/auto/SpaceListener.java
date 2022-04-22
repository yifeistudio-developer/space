package com.yifeistudio.space.starter.auto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yifeistudio.space.unit.util.Jsons;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import javax.annotation.Resource;

/**
 * @author : hongyi
 * created at 2022/4/20 - 11:06
 **/
@Slf4j
class SpaceListener implements ApplicationListener<ApplicationEvent> {

    @Resource
    private ObjectMapper objectMapper;


    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {

        }
    }
}

package com.yifeistudio.space.starter.auto;

import com.yifeistudio.space.unit.util.Jsons;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author : hongyi
 * created at 2022/4/20 - 11:06
 **/
@Slf4j
class SpaceListener implements ApplicationListener<ApplicationEvent> {


    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationStartedEvent) {
            Jsons.stringify(event).ifPresent(System.out::println);
            log.info("application started.");
        }
    }
}

package com.yifeistudio.space.starter.auto;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * SpringContext 工具
 *
 * @author : hongyi
 * created at 2022/4/27 - 19:29
 **/
@Slf4j
@Component
public class SpringContextHelper implements ApplicationContextAware {

    /**
     * -- GETTER --
     *  获取Spring 上下文
     *
     */
    @Getter
    private static Optional<ApplicationContext> applicationContext = Optional.empty();

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        SpringContextHelper.applicationContext = Optional.of(applicationContext);
    }

    /**
     * 获取Spring 上下文指定类型Bean
     *
     * @param clz 类型
     * @return Bean
     * @param <T> 类型
     */
    public static <T> Optional<T> getBean(Class<T> clz) {
        return applicationContext.map(context -> context.getBean(clz));
    }

}
///~
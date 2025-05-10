package com.yifeistudio.space.starter.auto;

import com.yifeistudio.space.unit.AssertException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Optional;

/**
 * SpringContext 工具
 *
 * @author : hongyi
 * created at 2022/4/27 - 19:29
 **/
@Slf4j
public class SpringContextHelper implements ApplicationContextAware {

    /**
     * -- GETTER --
     *  获取Spring 上下文
     *
     */
    @Getter
    private static Optional<ApplicationContext> applicationContext = Optional.empty();

    /**
     * 设置上下文
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws BeansException BeansException
     */
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

    /**
     * 获取Spring 上下文指定类型Bean
     * - 确定不会为空的情况
     * - 为空的情况将会抛出异常
     * @param clz 类
     * @return Bean
     * @param <T> 类型
     */
    public static <T> T getNonNullBean(Class<T> clz) {
        return getBean(clz).orElseThrow(() -> new AssertException(String.format("指定类型<%s>Bean不存在！", clz)));
    }
}
///~
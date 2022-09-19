package com.yifeistudio.space.starter.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * Logback 配置
 *
 * @author : hongyi
 * created at 2022/4/27 - 10:29
 **/
class LogbackConfig implements EnvironmentAware {

    private Environment environment;

    /**
     * Set the {@code Environment} that this component runs in.
     *
     * @param environment 环境配置
     */
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }





}

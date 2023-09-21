package com.yifeistudio.space.starter.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.Resource;

/**
 * 配置
 *
 * @author yi
 * <p>
 * Created at 2023/9/21 上午9:33
 * @since 1.0
 */
@AutoConfiguration
@EnableConfigurationProperties(SpaceProperties.class)
public class SpaceConfiguration {

    @Resource
    private SpaceProperties properties;







}

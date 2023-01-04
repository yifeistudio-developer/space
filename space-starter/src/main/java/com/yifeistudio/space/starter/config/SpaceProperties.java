package com.yifeistudio.space.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Space 基础配置
 *
 * @author : hongyi
 * created at 2022/4/22 - 11:15
 **/
@Data
@ConfigurationProperties(prefix = "yifeistudio.space")
public class SpaceProperties {

    /**
     * 是否启用
     */
    private boolean isEnable;





}

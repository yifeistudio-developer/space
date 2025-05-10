package com.yifeistudio.space.starter.auto;

import com.yifeistudio.space.starter.config.SpaceProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * 配置
 *
 * @author yi
 * <p>
 * Created at 2023/9/21 上午9:33
 * @since 1.0
 */
@AutoConfiguration
@Import(value = {SpringContextHelper.class})
@EnableConfigurationProperties(SpaceProperties.class)
@ConditionalOnProperty(value = "yifeistudio.space.enable", matchIfMissing = true)
public class SpaceAutoConfiguration {

}

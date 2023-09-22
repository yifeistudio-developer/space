package com.yifeistudio.space.starter.auto;

import com.yifeistudio.space.starter.Constants;
import com.yifeistudio.space.starter.config.SpaceProperties;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

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
@ConditionalOnProperty(value = "space.enable", matchIfMissing = true)
public class SpaceAutoConfiguration implements EnvironmentPostProcessor, BeanClassLoaderAware, Ordered {

    private ClassLoader beanClassLoader;

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        configBanner(environment);
        configNacos(environment);
    }

    private void configBanner(ConfigurableEnvironment environment) {
        String bannerLocation = "spring.banner.location";
        String banner = environment.getProperty(bannerLocation);
        if (banner == null) {
            System.setProperty(bannerLocation, "yi-banner.txt");
        }
    }

    private void configNacos(ConfigurableEnvironment environment) {
        String nacosAutoConfig = "com.alibaba.boot.nacos.config.autoconfigure.NacosConfigAutoConfiguration";
        try {
            Class.forName(nacosAutoConfig, false, beanClassLoader);
        } catch (ClassNotFoundException e) {
            // ignore e
            return;
        }
        String serverAddr = environment.getProperty("nacos.config.server-addr");
        if (serverAddr == null) {
            System.setProperty("nacos.config.server-addr", Constants.NACOS_ADDR);
        }
    }

    // 优先集仅在SpringBoot 默认处理器之后
    @Override
    public int getOrder() {
        return Integer.MIN_VALUE + 10 + 1;
    }

    @Override
    public void setBeanClassLoader(@NotNull ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }
}

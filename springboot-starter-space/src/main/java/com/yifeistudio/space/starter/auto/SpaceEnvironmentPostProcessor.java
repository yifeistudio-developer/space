package com.yifeistudio.space.starter.auto;

import com.yifeistudio.space.starter.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 运行环境变量处理器
 *
 * @author yi
 * <p>
 * Created at 2023/9/22 下午2:48
 * @since 1.0
 */
@Slf4j
public class SpaceEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        configBanner(environment);
        configNacos(environment);
    }

    /**
     * 配置banner
     *
     * @param environment 环境
     */
    private void configBanner(ConfigurableEnvironment environment) {
        String bannerLocation = "spring.banner.location";
        String banner = environment.getProperty(bannerLocation);
        if (banner != null) return;
        if (log.isInfoEnabled()) {
            log.info("the banner of yifeistudio will be used: yi-banner");
        }
        System.setProperty(bannerLocation, "yi-banner.txt");
    }

    private void configNacos(ConfigurableEnvironment environment) {
        String nacosAutoConfig = "com.alibaba.boot.nacos.config.autoconfigure.NacosConfigAutoConfiguration";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            Class.forName(nacosAutoConfig, false, classLoader);
        } catch (ClassNotFoundException e) {
            // ignore e
            if (log.isInfoEnabled()) {
                log.info("nacos auto configuration is not detected.");
            }
            return;
        }
        String serverAddr = environment.getProperty("nacos.config.server-addr");
        if (serverAddr != null) return;
        if (log.isInfoEnabled()) {
            log.info("the default nacos server-addr will be used: {}", Constants.NACOS_ADDR);
        }
        System.setProperty("nacos.config.server-addr", Constants.NACOS_ADDR);
    }

    // 优先集仅在SpringBoot 默认处理器之后
    @Override
    public int getOrder() {
        return Integer.MIN_VALUE + 10 + 1;
    }
}
///~
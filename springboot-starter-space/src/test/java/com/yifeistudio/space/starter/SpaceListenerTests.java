package com.yifeistudio.space.starter;

import com.yifeistudio.space.starter.auto.SpaceListener;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 监听器自动装配测试
 *
 * @author yi
 * <p>
 * Created at 2023/9/21 上午9:04
 * @since 1.0
 */
public class SpaceListenerTests {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(SpaceListener.class));

    @Test
    void loadTest() {
        contextRunner.withUserConfiguration(SpaceListener.class).run(ctx -> {
            assertThat(ctx).hasSingleBean(SpaceListener.class);
            assertThat(ctx)
                    .getBean("spaceListener")
                    .isSameAs(ctx.getBean(SpaceListener.class));
        });
    }


}

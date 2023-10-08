package com.yifeistudio.space.web.starter.auto.react;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.codec.CodecCustomizer;
import org.springframework.http.codec.CodecConfigurer;

/**
 * 结果处理器
 *
 * @author : hongyi
 * created at 2022/12/19 - 15:07
 **/
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
class ReactiveResultHandler implements CodecCustomizer {


    @Override
    public void customize(CodecConfigurer configurer) {
        CodecConfigurer.DefaultCodecs defaultCodecs = configurer.defaultCodecs();

    }




}

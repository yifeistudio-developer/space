/**
 * Space-Starter 模块
 *
 * @author : hongyi
 * created at 2022/9/13 - 16:45
 **/
module space.starter {

    requires lombok;
    requires spring.boot;
    requires spring.context;
    requires spring.core;
    requires spring.beans;

    exports com.yifeistudio.space.starter.auto;
    exports com.yifeistudio.space.starter.config;
    exports com.yifeistudio.space.starter.model;
}
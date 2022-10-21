package com.yifeistudio.space.example

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@NacosPropertySource(autoRefreshed = true, dataId = "space-example-config")
@SpringBootApplication
class SpaceExampleApplication

fun main(args: Array<String>) {
    runApplication<SpaceExampleApplication>(*args)
}



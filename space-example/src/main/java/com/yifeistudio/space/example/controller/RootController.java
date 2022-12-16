package com.yifeistudio.space.example.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.yifeistudio.space.unit.model.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * File Description
 *
 * @author : hongyi
 * created at 2022/10/19 - 15:00
 **/

@RestController
class RootController {


    @NacosValue(value = "${application.name:aaa}", autoRefreshed = true)
    private String applicationName;

    @GetMapping("/ac")
    public Result<?> index() {
        return Result.success();
    }

}

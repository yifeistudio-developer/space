package com.yifeistudio.space.example.controller

import com.yifeistudio.space.unit.model.Result
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * index controller.
 *
 * @author : hongyi
 * created at 2022/9/20 - 15:42
 **/
@RestController
class IndexController {
    @GetMapping("/abc")
    fun index(): Result<Any> {
        return Result.success()
    }
}




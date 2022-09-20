package com.yifeistudio.space.example.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * index controller.
 *
 * @author : hongyi
 * created at 2022/9/20 - 15:42
 **/
@RestController
class IndexController

@GetMapping("/")
fun index(): String {
    return "xx"
}


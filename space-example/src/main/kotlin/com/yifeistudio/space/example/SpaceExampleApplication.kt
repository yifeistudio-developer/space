package com.yifeistudio.space.example

import com.yifeistudio.space.starter.SpaceApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SpaceExampleApplication

fun main(args: Array<String>) {
    SpaceApplication.run(SpaceExampleApplication::class.java, args)
}



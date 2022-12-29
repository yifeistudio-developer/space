package com.yifeistudio.space.starter

import com.yifeistudio.space.starter.auto.SpringContextHelper
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SpaceStarterApplicationTests {

    @Test
    fun jvmTest () {
        val applicationContext = SpringContextHelper.getApplicationContext()
        applicationContext.ifPresent {
            value -> println(value)
        }
    }

}

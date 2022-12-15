package com.yifeistudio.space.starter

import com.yifeistudio.space.starter.auto.SpringContextHelper
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [ApplicationTests::class])
class SpaceStarterApplicationTests {

    @Test
    fun jvmTest () {
        var context = SpringContextHelper.getApplicationContext().get()
        println(context)
    }

}

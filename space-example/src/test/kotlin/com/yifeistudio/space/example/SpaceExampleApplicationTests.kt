package com.yifeistudio.space.example

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import javax.annotation.Resource

@SpringBootTest
class SpaceExampleApplicationTests {

    private lateinit var mockMvc: MockMvc

    @Resource
    private lateinit var webApplicationContext :WebApplicationContext

    @BeforeEach
    fun init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .build()

    }

    @Test
    fun contextLoads() {
    }

    @Test
    fun get() {
        mockMvc.get("/")
            .andReturn()
            .response
            .contentAsString
            .apply(::println)
    }

}

package com.yifeistudio.space.unit.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * 无Spring 环境测试
 *
 * @author : hongyi
 * created at 2022/4/29 - 20:22
 **/
class NoSpringTests {

    @Test
    void jvmTest() {
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);
    }

    @Test
    void splitTest() {
        int size = 3;
        List<Integer> x = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            x.add(i);
        }
        long l = System.currentTimeMillis();
        List<List<Integer>> split = CollectionUtil.split(x, 8);
        System.out.println(System.currentTimeMillis() - l);
        System.out.println(Jsons.stringify(split).get());
    }

}

package com.yifeistudio.space.unit.model;

import java.util.List;

/**
 * 分页信息
 *
 * @author : hongyi
 * created at 2022/10/18 - 14:38
 **/
public record Page<T>(int count, List<T> records) {

}

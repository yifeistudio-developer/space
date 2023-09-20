package com.yifeistudio.space.unit.model;

/**
 * 树节点
 *
 * @param value 值
 * @param left  左节点
 * @param right 右节点
 * @param <T>   元素类型
 */
public record TreeNode<T>(T value, T left, T right) {

}

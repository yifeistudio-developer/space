package com.yifeistudio.space.unit.util;


import java.util.*;

/**
 * 集合工具
 *
 * @author : hongyi
 * created at 2022/5/9 - 20:35
 **/
public final class SpaceCollections {

    private SpaceCollections() {
    }

    /**
     * 判断集合是否为空
     *
     * @param collection 集合
     * @return 集合是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断集合是否为空
     *
     * @param collection 集合
     * @return 集合是否为空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断集合是否为空
     *
     * @param map 集合
     * @return 集合是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断集合是否为空
     *
     * @param map 集合
     * @return 集合是否为空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 集合拆分器
     *
     * @param collection 集合
     * @param size 拆分大小
     * @return 拆分结果
     * @param <T> 集合元素类型
     */
    public static <T> List<List<T>> split(Collection<T> collection, int size) {
        Asserts.notNull(collection, "collection is required nonNull");
        int colSize = collection.size();
        if (size <= 0 || colSize <= size) {
            return java.util.Collections.singletonList(new ArrayList<>(collection));
        }
        Object[] rawCol = collection.toArray();
        int len = rawCol.length;
        int p = 0;
        int tail;
        int rem = len % size == 0 ? 0 : 1;
        List<List<T>> rlt = new ArrayList<>(len / size + rem);
        while (p < len) {
            tail = Math.min(p + size, len);
            List<T> objs = new ArrayList<>();
            for (int i = p; i < tail; i++) {
                //noinspection unchecked
                objs.add((T) rawCol[i]);
            }
            rlt.add(objs);
            p = tail;
        }
        return rlt;
    }

}

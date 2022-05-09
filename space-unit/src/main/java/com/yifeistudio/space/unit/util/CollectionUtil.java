package com.yifeistudio.space.unit.util;


import java.util.*;

/**
 * 拆分工具
 *
 * @author : hongyi
 * created at 2022/5/9 - 20:35
 **/
public final class CollectionUtil {

    private CollectionUtil() {
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
            return Collections.singletonList(new ArrayList<>(collection));
        }
        int patchSize = colSize / size;
        int remain = colSize % size;
        if (remain > 0) {
            patchSize++;
        }
        Object[] rawCol = collection.toArray();
        List<List<T>> patches = new ArrayList<>(patchSize);
        int len;
        int index;
        for (int i = 0; i < patchSize; i++) {
            len = i == (patchSize - 1) && (remain > 0) ? remain : size;
            index = i * size;
            List<T> patch = new ArrayList<>(len);
            for (int j = 0; j < len; j++) {
                T t = (T) rawCol[index + j];
                patch.add(t);
            }
            patches.add(patch);
        }
        return patches;
    }

}

package com.yifeistudio.space.unit.util;


import java.util.*;

/**
 * 集合工具
 *
 * @author : hongyi
 * created at 2022/5/9 - 20:35
 **/
public final class Spliter {

    private Spliter() {
    }

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

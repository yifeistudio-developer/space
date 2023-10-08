package com.yifeistudio.space.unit.util;

import org.junit.jupiter.api.Test;

/**
 * 文件描述
 *
 * @author yi
 * <p>
 * Created at 2023/10/8 上午11:32
 * @since 1.0
 */
public class BitsTests {

    static class Masks {

        static int FLAG1 = (1 << 0);

        static int FLAG2 = (1 << 1);
        static int FLAG3 = (1 << 2);
        static int FLAG4 = (1 << 3);

    }


    @Test
    void addTest() {
        int attr = 0;
        attr = Bits.add(attr, Masks.FLAG1);
        System.out.println(attr);
        attr = Bits.add(attr, Masks.FLAG2);
        System.out.println(attr);
        attr = Bits.add(attr, Masks.FLAG3);
        System.out.println(attr);
        attr = Bits.add(attr, Masks.FLAG4);
        System.out.println(attr);
    }

    @Test
    void removeTest() {

        int attr = 0;
        attr = Bits.add(attr, Masks.FLAG1, Masks.FLAG2, Masks.FLAG3, Masks.FLAG4);
        System.out.println(attr);
        attr = Bits.remove(attr, Masks.FLAG1);
        System.out.println(attr);
        attr = Bits.remove(attr, Masks.FLAG2);
        System.out.println(attr);
        attr = Bits.remove(attr, Masks.FLAG3);
        System.out.println(attr);
        attr = Bits.remove(attr, Masks.FLAG4);
        System.out.println(attr);

    }

    @Test
    void hasMaskTest() {
        int attr = 0;
        attr = Bits.add(attr, Masks.FLAG1, Masks.FLAG2, Masks.FLAG3, Masks.FLAG4);
        System.out.println(Bits.hasMask(attr, Masks.FLAG1));
        attr = Bits.remove(attr, Masks.FLAG1);
        System.out.println(Bits.hasMask(attr, Masks.FLAG1));
    }
}

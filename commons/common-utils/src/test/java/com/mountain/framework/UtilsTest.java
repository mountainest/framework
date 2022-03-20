package com.mountain.framework;

import org.junit.jupiter.api.Test;

class UtilsTest {
    @Test
    void test1() {
        String time = Utils.fmtTime();
        System.out.println(time);
    }
}
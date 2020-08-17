package com.laowang.datasource.guava.collections;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    @Test
    public void testPartition() {
        ArrayList<String> list = Lists.newArrayList("1", "2", "3", "4");
        // partition的第二个参数是指拆分后的每个list的容量
        List<List<String>> result = Lists.partition(list, 3);
        System.out.println(result.get(0));
        System.out.println(result.get(1));
    }
}

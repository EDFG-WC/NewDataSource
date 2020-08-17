package com.laowang.datasource.guava.collections;

import com.google.common.collect.Sets;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;


public class SetTest {
    @Test
    public void testCreate() {
        HashSet<Integer> set = Sets.newHashSet(1, 2, 3);
        assertThat(set.size(), equalTo(3));
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 3);
        assertThat(list.size(), equalTo(4));
        HashSet<Integer> set2 = Sets.newHashSet(list);
        assertThat(set2.size(), equalTo(3));
    }

    @Test
    public void testCartesianProduct() {
        // 3个set里每次拿1个元素, 产生出的所有排列:
        Set<List<Integer>> set = Sets.cartesianProduct(Sets.newHashSet(1, 2), Sets.newHashSet(3, 4), Sets.newHashSet(5, 6));
        System.out.println(set);
    }

    @Test
    public void testCombinations() {
        HashSet<Integer> set = Sets.newHashSet(1, 2, 3);
        Set<Set<Integer>> combinations = Sets.combinations(set, 2);
        combinations.forEach(System.out::println);
    }
}

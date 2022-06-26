package com.advanced.modernJavaInAction.part1.predicate;

import com.advanced.modernJavaInAction.part1.domain.Apple;

public interface ApplePredicate {
    boolean test(Apple apple);
}

package com.advanced.modernJavaInAction.part1.predicate;

import com.advanced.modernJavaInAction.part1.domain.Apple;

public class AppleHeavyWeightPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }

}

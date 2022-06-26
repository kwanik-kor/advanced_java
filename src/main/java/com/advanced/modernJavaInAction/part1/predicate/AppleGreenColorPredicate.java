package com.advanced.modernJavaInAction.part1.predicate;

import com.advanced.modernJavaInAction.part1.domain.Apple;
import com.advanced.modernJavaInAction.part1.type.Color;

public class AppleGreenColorPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return Color.GREEN.equals(apple.getColor());
    }

}

package com.advanced.modernJavaInAction.part1.domain;

import com.advanced.modernJavaInAction.part1.type.Color;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Apple {
    private Color color;
    private Integer weight;

    @Builder
    public Apple(Color color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public static boolean isGreenApple(Apple apple) {
        return Color.GREEN == apple.getColor();
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }
}

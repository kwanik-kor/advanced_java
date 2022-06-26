package com.advanced.modernJavaInAction.chapter1;

import com.advanced.modernJavaInAction.chapter1.domain.Apple;
import com.advanced.modernJavaInAction.chapter1.type.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FromJava8 {

    @Autowired
    private MethodToFirstClass service;

    private static List<Apple> apples = new ArrayList<>();

    @Test
    public void 사과_필터링_하기() {
        List<Apple> colorFiltered = service.filterApple(apples, Apple::isGreenApple);
        assertEquals(2, colorFiltered.size());

        List<Apple> weightFiltered = service.filterApple(apples, Apple::isHeavyApple);
        assertEquals(2, weightFiltered.size());
    }

    @Test
    public void 람다로_사과_필터링_하기() {
        List<Apple> colorFiltered = service.filterApple(apples, (Apple a) -> Color.GREEN == a.getColor());
        assertEquals(2, colorFiltered.size());

        List<Apple> weightFiltered = service.filterApple(apples, (Apple a) -> a.getWeight() > 150);
        assertEquals(2, weightFiltered.size());
    }

    @BeforeAll
    static void initializeApples() {
        int[] weights = {100, 120, 160, 170};

        for (int i = 0; i < weights.length; i++) {
            apples.add(Apple.builder()
                    .color(i % 2 == 0 ? Color.RED : Color.GREEN)
                    .weight(weights[i])
                    .build());
        }
    }
}

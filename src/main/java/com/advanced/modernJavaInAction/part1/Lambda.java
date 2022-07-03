package com.advanced.modernJavaInAction.part1;

import com.advanced.modernJavaInAction.part1.domain.Apple;

import java.util.Comparator;

public class Lambda {

    public void compareLambda() {
        // 기존 코드
        Comparator<Apple> byWeight = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        };

        // By Lambda
        Comparator<Apple> byWeight2 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
    }

    /**
     * Functional Interface Annotation을 통해서 추상 메서드가 한 개 이상인 경우 Error를 발생시킬 수 있음
     * @param <T>
     */
    @java.lang.FunctionalInterface
    private interface FunctionalInterface<T> {
        boolean test(T t);
    }
}

package com.advanced.modernJavaInAction.part1;

import com.advanced.modernJavaInAction.part1.domain.Apple;
import com.advanced.modernJavaInAction.part1.type.Color;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

/**
 * 람다 표현식 간의 조합을 통한 가치 창출
 */
public class CombinationOfLambda {

    /**
     * Comparator 의 조합
     */
    public void comparatorCombination(List<Apple> inventory) {
        // 1. 역정렬
        inventory.sort(comparing(Apple::getWeight).reversed());
        // 2. 연결
        inventory.sort(comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));
    }

    /**
     * Predicate 의 조합
     */
    public void predicateCombination() {
        Predicate<Apple> redApple = (a) -> Color.RED.equals(a.getColor());

        // 1. 반전
        Predicate<Apple> notRedApple = redApple.negate();

        // 2. 조합
        Predicate<Apple> redAndHeavyApple = redApple.and(apple -> apple.getWeight() > 150);
        Predicate<Apple> redAndHeavyAppleOrGreen = redApple
                .and(apple -> apple.getWeight() > 150)
                .or(apple -> Color.GREEN.equals(apple.getColor()));
    }

    /**
     * Function 의 조합
     */
    public void functionCombination() {
        // 1. andThen : 주어진 함수를 먼저 적용한 결과를 다른 함수의 입력으로 전달하는 함수를 반환
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(1); // = (1 + 1) * 2 = 4

        // 2. compose : 주어진 함수를 먼저 실행한 다음에 그 결과를 외부 함수의 인수로 제공
        Function<Integer, Integer> i = f.compose(g);
        result = h.apply(1); // = (1 * 2) + 1 = 3
    }
}

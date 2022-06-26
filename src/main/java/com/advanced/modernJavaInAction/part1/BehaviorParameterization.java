package com.advanced.modernJavaInAction.part1;

import com.advanced.modernJavaInAction.part1.domain.Apple;
import com.advanced.modernJavaInAction.part1.predicate.ApplePredicate;
import com.advanced.modernJavaInAction.part1.type.Color;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Chapter 2. 동작파라미터화
 */
@Service
public class BehaviorParameterization {

    /**
     * 1) 첫 번째 시도: 녹색 사과 필터링
     */
    public List<Apple> filterGreenApples_1(List<Apple> inventory) {
        List<Apple> ret = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor() == Color.GREEN) {
                ret.add(apple);
            }
        }
        return ret;
    }

    /**
     * 2) 두 번째 시도: 색을 파라미터화
     */
    public List<Apple> filterApplesByColor_2(List<Apple> inventory, Color color) {
        List<Apple> ret = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor() == color) {
                ret.add(apple);
            }
        }
        return ret;
    }

    /**
     * 3) 세 번째 시도: 가능한 모든 속성으로 필터링
     */
    public List<Apple> filterApples_3(List<Apple> inventory, Color color, int weight, boolean flag) {
        List<Apple> ret = new ArrayList<>();
        for (Apple apple : inventory) {
            if ((flag && apple.getColor().equals(color))
                    || (!flag && apple.getWeight() > weight)) {
                ret.add(apple);
            }
        }
        return ret;
    }

    // 동작 파라미터화 적용하기!
    /**
     * 4) 네 번째 시도: 추상적 조건으로 필터링
     */
    public List<Apple> filterApples_4(List<Apple> inventory, ApplePredicate p) {
        List<Apple> ret = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                ret.add(apple);
            }
        }
        return ret;
    }

    // 복잡한 과정 간소화하기!

    /**
     * 5) 다섯 번째 시도: 익명 클래스 사용
     */
    public List<Apple> filterRedApples_5(List<Apple> inventory) {
        List<Apple> ret = filterApples_4(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return Color.RED.equals(apple.getColor());
            }
        });
        return ret;
    }

    /**
     * 6) 여섯 번째 시도: 람다 표현식 사용
     */
    public List<Apple> filterRedApples_6(List<Apple> inventory) {
        List<Apple> ret = filterApples_4(inventory, (Apple apple) -> Color.RED.equals(apple.getColor()));
        return ret;
    }

    /**
     * 7) 일곱 번째 시도: 리스트 형식으로 추상화
     */
    public <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> ret = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                ret.add(e);
            }
        }
        return ret;
    }

    public void filter_7(List<Apple> inventory) {
        List<Apple> greenApples = filter(inventory, (Apple apple) -> Color.GREEN.equals(apple.getColor()));

        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9 ,10));
        List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);
    }
}

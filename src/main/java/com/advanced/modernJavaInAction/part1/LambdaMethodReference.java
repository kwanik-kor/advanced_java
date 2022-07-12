package com.advanced.modernJavaInAction.part1;

import com.advanced.modernJavaInAction.part1.domain.Apple;

import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

/**
 * 람다, 메서드 참조를 활용해 코드 개선하기
 */
public class LambdaMethodReference {

    // 1단계: 코드 전달
    public class AppleComparator implements Comparator<Apple> {
        public int compare(Apple a1, Apple a2) {
            return a1.getWeight().compareTo(a2.getWeight());
        }
    }
    public void firstStep(List<Apple> inventory) {
        inventory.sort(new AppleComparator());
    }

    // 2단계: 익명 클래스 사용
    public void secondStep(List<Apple> inventory) {
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
    }

    // 3단계: 람다 표현식 사용
    public void thirdStep(List<Apple> inventory) {
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        // 더 간소화 시키기
        inventory.sort(comparing(apple -> apple.getWeight()));
    }

    // 4단계: 메서드 참조 사용
    public void fourthStep(List<Apple> inventory) {
        inventory.sort(comparing(Apple::getWeight));
    }
}

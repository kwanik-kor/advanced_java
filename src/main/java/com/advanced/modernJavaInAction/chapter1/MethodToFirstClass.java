package com.advanced.modernJavaInAction.chapter1;

import com.advanced.modernJavaInAction.chapter1.domain.Apple;
import com.advanced.modernJavaInAction.chapter1.type.Color;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class MethodToFirstClass {

    /*
     * BEFORE JAVA 8
     * - 코드의 중복, 불분명한 추상화 레벨
     */
    public List<Apple> collectGreenAppleBeforeJava8(List<Apple> inventory) {
        List<Apple> ret = new ArrayList<>();
        for (Apple apple : inventory) {
            if (Color.GREEN == apple.getColor()) {
                ret.add(apple);
            }
        }
        return ret;
    }

    public List<Apple> collectOverWeightAppleBeforeJava8(List<Apple> inventory) {
        List<Apple> ret = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                ret.add(apple);
            }
        }
        return ret;
    }

    /*
     * FROM JAVA 8
     */
    public List<Apple> filterApple(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> ret = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                ret.add(apple);
            }
        }
        return ret;
    }

}

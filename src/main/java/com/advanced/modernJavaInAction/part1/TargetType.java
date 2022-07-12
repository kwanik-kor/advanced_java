package com.advanced.modernJavaInAction.part1;

import com.advanced.modernJavaInAction.part1.domain.Apple;
import com.advanced.modernJavaInAction.part1.type.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class TargetType {

    /**
     * 자바 컴파일러는 람다 표현식의 파라미터 형식에 접근할 수 있으므로 람다 문법을 생략할 수 있다!
     *  - 방법에 대해서는 정해진 규칙은 없으나, 가독성 향상을 위한 방향을 결정해야 함.
     */
    public void guessTargetType() {
        Comparator<Apple> comp_1 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
        Comparator<Apple> comp_2 = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());
    }

    /**
     * 람다는 인스턴스 변수나 정적 변수를 자유롭게 캡처링할 수 있으나,
     * final 로 선언되어 있거나 final 처럼 사용되는 값에 한에 사용할 수 있다.
     */
    public void capturingLambda() {
        final int dev_port = 8080;
        Runnable r = () -> System.out.println(dev_port);

        int local_port = 8081;
        Runnable r2 = () -> System.out.println(local_port);
//        local_port = 8082;
    }

    /**
     * 메서드 참조
     */
    public void referenceMethod() {
        List<Apple> inventory= new ArrayList<>();
        inventory.add(new Apple(Color.GREEN, 1400));

        // 기존 코드
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

        // 메서드 참조
        inventory.sort(comparing(Apple::getWeight));
    }

    public void convertLambdaToMethodReference() {
        // 1번
        ToIntFunction<String> stringToInt = (String s) -> Integer.parseInt(s);
        Function<String, Integer> stringToInteger = Integer::parseInt;

        // 2번
        BiPredicate<List<String>, String> contains = (list, element) -> list.contains(element);
        BiPredicate<List<String>, String> contains2 = List::contains;

        // 3번
        Predicate<String> startsWithNumber = (String string) -> this.startsWithNumber(string);
        Predicate<String> startsWithNumber2 = this::startsWithNumber;
    }

    boolean startsWithNumber(String str) {
        return str.startsWith("[0-9]");
    }


    /**
     * 생성자 참조
     */
    public void createDefaultApples() {
        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, Apple::new);
    }

    public List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
        return list.stream()
            .map(f)
            .collect(Collectors.toList());
    }
}

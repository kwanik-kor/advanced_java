package com.advanced.modernJavaInAction.part1;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ToFunctionalInterface {

    /**
     * 1. try-with-resource 구문을 활용한 '실행-어라운드 패턴'
     * @return 한 줄 읽기
     *
     * @throws IOException
     */
    public String processFile_1() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            return br.readLine();
        }
    }

    /**
     * 2. 동작 파라미터화 / 함수형 인터페이스 / 람다를 이용해 재활용성이 높은 코드로 탄생시키기
     *  - 내부적으로 동작이 정의되어 있던 코드에서 사용자의 변화에 유연하게 대응할 수 있는 코드로 바뀜
     *  - 함수형 인터페이스를 선언하는 이유는, 그래야 람다를 파라미터로 넘길 수 있기 때문이다!
     *    > 함수형 인터페이스의 추상 메서드 구현을 람다 표현식으로 전달할 수 있음
     */
    public void makeResult() throws IOException{
        String oneLine = processFile(BufferedReader::readLine);
        String twoLines = processFile((BufferedReader br) -> br.readLine() + br.readLine());
    }

    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader br) throws IOException;
    }

    public String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            return p.process(br);
        }
    }

    /**
     * 대표적인 자바 8 함수형 인터페이스
     *  - Predicate : Boolean 표현식이 필요한 상황에서 사용
     *  - Consumer  : T 형식의 객체를 인수로 받아서 어떤 동작을 수행하고 싶을 때 사용(return void)
     *  - Function  : T 형식의 객체를 인수로 받아서 R 형식의 객체를 반환하는 apply 메서드 제공
     */
    public <T> List<T> filter(List<T> list, Predicate<T> p) {
        return list.stream()
                .filter(p)
                .collect(Collectors.toList());
    }

    public <T> void forEach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }
    public void testConsumer() {
        forEach(Arrays.asList(1, 2, 3, 4, 5), System.out::println);
    }

    public <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }
    public void testFunction() {
        List<Integer> result = map(Arrays.asList("1", "2", "3"), Integer::parseInt);
    }

    /**
     * 기본형을 입출력으로 사용하는 경우 오토박싱을 피할 수 있는 특별한 함수형 인터페이스를 제공함
     */
    public void primitiveTypeInterface() {
        // 박싱 없음
        IntPredicate evenNumbers = (int i) -> i % 2 == 0;
        printResult(evenNumbers.test(1000));

        // 박싱
        Predicate<Integer> oddNumbers = (Integer i) -> i % 2 != 0;
        printResult(oddNumbers.test(1000));
    }

    public void printResult(boolean result) {
        System.out.println(result? "TRUE" : "FALSE");
    }
}

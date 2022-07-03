package com.advanced.modernJavaInAction.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

}

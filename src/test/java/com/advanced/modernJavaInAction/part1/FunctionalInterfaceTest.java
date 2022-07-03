package com.advanced.modernJavaInAction.part1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FunctionalInterfaceTest {

    @Autowired
    private ToFunctionalInterface service;

    @Test
    public void 기본형_함수형_인터페이스_테스트() {
        service.primitiveTypeInterface();
    }
}

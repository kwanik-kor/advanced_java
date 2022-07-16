package com.advanced.modernJavaInAction.part2;

import com.advanced.modernJavaInAction.part2.domain.Dish;
import com.advanced.modernJavaInAction.part2.domain.Menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 외부 반복과 내부 반복에 대해...
 */
public class ExternalToInternal {

    /**
     * 외부 반복: 컬렉션
     */
    public List<String> externalIterator() {
        List<String> highCaloricDishes = new ArrayList<>();
        Iterator<Dish> iterator = Menu.menu.iterator();
        while (iterator.hasNext()) {
            Dish dish = iterator.next();
            if (dish.getCalories() > 300) {
                highCaloricDishes.add(dish.getName());
            }
        }
        return highCaloricDishes;
    }

    /**
     * 내부 반복: 스트림
     */
    public List<String> internalIterator() {
        return Menu.menu.stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .collect(Collectors.toList());
    }
}

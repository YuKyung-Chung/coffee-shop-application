package com.yukyung.coffeeshop.coffee.service;

import com.yukyung.coffeeshop.coffee.entity.Coffee;
import com.yukyung.coffeeshop.coffee.repository.CoffeeRepository;
import com.yukyung.coffeeshop.member.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {

    public Coffee createCoffee(Coffee coffee) {
        return coffee;
    }

    public Coffee updateCoffee(Coffee coffee) {
        return coffee;
    }

    public Coffee findCoffee(long coffeeId) {
        return new Coffee(coffeeId, "아메리카노", "Americano", 2500);
    }

    public List<Coffee> findCoffees() {
        return List.of(
                new Coffee(1L, "아메리카노", "Americano", 2500),
                new Coffee(2L, "카라멜 라뗴", "Caramel Latte", 5000)
        );
    }

    public void deleteCoffee(long coffeeId) {
    }
}

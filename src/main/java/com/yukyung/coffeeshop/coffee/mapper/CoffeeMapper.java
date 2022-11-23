package com.yukyung.coffeeshop.coffee.mapper;

import com.yukyung.coffeeshop.coffee.dto.CoffeePatchDto;
import com.yukyung.coffeeshop.coffee.dto.CoffeePostDto;
import com.yukyung.coffeeshop.coffee.dto.CoffeeResponseDto;
import com.yukyung.coffeeshop.coffee.entity.Coffee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {
    public Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto);

    public Coffee coffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto);

    public CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee);
}

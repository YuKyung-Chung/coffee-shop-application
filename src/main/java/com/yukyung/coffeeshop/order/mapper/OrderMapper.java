package com.yukyung.coffeeshop.order.mapper;

import com.yukyung.coffeeshop.coffee.entity.Coffee;
import com.yukyung.coffeeshop.coffee.service.CoffeeService;
import com.yukyung.coffeeshop.order.dto.OrderCoffeeResponseDto;
import com.yukyung.coffeeshop.order.dto.OrderPostDto;
import com.yukyung.coffeeshop.order.dto.OrderResponseDto;
import com.yukyung.coffeeshop.order.entity.CoffeeRef;
import com.yukyung.coffeeshop.order.entity.Order;
import org.mapstruct.Mapper;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public class OrderMapper {
    public Order orderPostDtoToOrder(OrderPostDto orderPostDto) {
        Order order = new Order();
        order.setMemberId(
                new AggregateReference.IdOnlyAggregateReference<>(orderPostDto.getMemberId())
        );
        Set<CoffeeRef> orderCoffees = orderPostDto.getOrderCoffees()
                .stream()
                .map(orderCoffeeDto -> new CoffeeRef(orderCoffeeDto.getCoffeeId(),
                        orderCoffeeDto.getQuantity()))
                .collect(Collectors.toSet());
        order.setOrderCoffees(orderCoffees);
        return order;
    }

    public OrderResponseDto orderToOrderResponseDto(CoffeeService coffeeService, Order order) {
        long memberId = order.getMemberId().getId();

        List<OrderCoffeeResponseDto> orderCoffees =
                orderToOrderCoffeeResponseDto(coffeeService, order.getOrderCoffees());

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderCoffees(orderCoffees);
        orderResponseDto.setMemberId(memberId);
        orderResponseDto.setCreatedAt(order.getCreatedAt());
        orderResponseDto.setOrderId(order.getOrderId());
        orderResponseDto.setOrderStatus(order.getOrderStatus());

        return orderResponseDto;
    }

    List<OrderCoffeeResponseDto> orderToOrderCoffeeResponseDto(CoffeeService coffeeService,
                                                               Set<CoffeeRef> orderCoffees) {
        return orderCoffees.stream()
                .map(coffeeRef -> {
                    Coffee coffee = coffeeService.findCoffee(coffeeRef.getCoffeeId());

                    return new OrderCoffeeResponseDto(coffee.getCoffeeId(),
                            coffee.getKorName(),
                            coffee.getEngName(),
                            coffee.getPrice(),
                            coffeeRef.getQuantity());
                }).collect(Collectors.toList());
    }
}

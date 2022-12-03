package com.yukyung.coffeeshop.order.controller;

import com.yukyung.coffeeshop.coffee.service.CoffeeService;
import com.yukyung.coffeeshop.order.dto.OrderPostDto;
import com.yukyung.coffeeshop.order.dto.OrderResponseDto;
import com.yukyung.coffeeshop.order.entity.Order;
import com.yukyung.coffeeshop.order.mapper.OrderMapper;
import com.yukyung.coffeeshop.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v10/orders")
@Validated
public class OrderController {
    private final static String ORDER_DEFAULT_URL = "/v10/orders"; // Default URL 경로
    private final OrderService orderService;
    private final OrderMapper mapper;
    private final CoffeeService coffeeService;

    public OrderController(OrderService orderService,
                           OrderMapper mapper,
                           CoffeeService coffeeService) {
        this.orderService = orderService;
        this.mapper = mapper;
        this.coffeeService = coffeeService;
    }

    @PostMapping
    public ResponseEntity postOrder(@Valid @RequestBody OrderPostDto orderPostDto) {
        Order order = orderService.createOrder(mapper.orderPostDtoToOrder(orderPostDto));

        //등록된 주문에 해당하는 URI 객체
        URI location =
                UriComponentsBuilder
                        .newInstance()
                        .path(ORDER_DEFAULT_URL + "/{order-id}")
                        .buildAndExpand(order.getOrderId())
                        .toUri(); // "/v10/orders/{order-id}"

        return ResponseEntity.created(location).build(); // HTTP 201 Created status
    }

    @GetMapping("/{order-id}")
    public ResponseEntity getOrder(@PathVariable("order-id") @Positive long orderId) {
        Order order = orderService.findOrder(orderId);

        // 주문한 커피 정보 가져오도록 수정
        return new ResponseEntity<>(mapper.orderToOrderResponseDto(coffeeService, order),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getOrders() {
        List<Order> orders = orderService.findOrders();

        // 주문한 커피 정보 가져오도록 수정
        List<OrderResponseDto> response =
                orders.stream()
                        .map(order -> mapper.orderToOrderResponseDto(coffeeService, order))
                        .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{order-id}")
    public ResponseEntity cancelOrder(@PathVariable("order-id") @Positive long orderId) {
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

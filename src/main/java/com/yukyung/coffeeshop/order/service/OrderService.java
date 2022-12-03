package com.yukyung.coffeeshop.order.service;

import com.yukyung.coffeeshop.coffee.service.CoffeeService;
import com.yukyung.coffeeshop.exception.BusinessLogicException;
import com.yukyung.coffeeshop.exception.ExceptionCode;
import com.yukyung.coffeeshop.member.service.MemberService;
import com.yukyung.coffeeshop.order.entity.Order;
import com.yukyung.coffeeshop.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    final private OrderRepository orderRepository;
    final private MemberService memberService;
    final private CoffeeService coffeeService;

    public OrderService(OrderRepository orderRepository,
                        MemberService memberService,
                        CoffeeService coffeeService) {
        this.orderRepository = orderRepository;
        this.memberService = memberService;
        this.coffeeService = coffeeService;
    }

    public Order createOrder(Order order) {
        //회원이 존재하는지 확인
        memberService.findVerifiedMember(order.getMemberId().getId());

        //커피가 존재하는지 확인
        order.getOrderCoffees()
                .stream()
                .forEach(coffeeRef -> {
                    coffeeService.findVerifiedCoffee(coffeeRef.getCoffeeId());
                });
        return orderRepository.save(order);
    }

    public Order findOrder(long orderId) {
        return findVerifiedOrder(orderId);
    }

    public List<Order> findOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    public void cancelOrder(long orderId) {
        Order findOrder = findVerifiedOrder(orderId);
        int step = findOrder.getOrderStatus().getStepNumber();

        //OrderStatus의 step이 2미만일 경우(ORDER_CONFIRM)에만 주문취소가 되도록 한다
        if (step >= 2) {
            throw new BusinessLogicException(ExceptionCode.CANNOT_CHANGE_ORDER);
        }

        findOrder.setOrderStatus(Order.OrderStatus.ORDER_CANCEL);
        orderRepository.save(findOrder);
    }

    private Order findVerifiedOrder(long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Order findOrder =
                optionalOrder.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ORDER_NOT_FOUND));
        return findOrder;
    }
}

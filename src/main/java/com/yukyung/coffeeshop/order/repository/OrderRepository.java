package com.yukyung.coffeeshop.order.repository;

import com.yukyung.coffeeshop.order.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}

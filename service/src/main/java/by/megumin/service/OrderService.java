package by.megumin.service;

import by.megumin.entity.orderEntity.Order;
import by.megumin.entity.userEntity.User;
import by.megumin.service.common.BaseService;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService extends BaseService<Order> {
    List<Order> getByUser(User owner);
    Order createOrder(User user);
}
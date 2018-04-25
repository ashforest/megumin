package by.megumin.service;

import by.megumin.entity.orderEntity.Order;
import by.megumin.entity.orderEntity.OrderContent;
import by.megumin.service.common.BaseService;

import java.util.List;

public interface OrderContentService extends BaseService<OrderContent> {
    List<OrderContent> getByOrder(Order order);
}

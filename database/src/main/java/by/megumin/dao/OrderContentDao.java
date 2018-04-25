package by.megumin.dao;

import by.megumin.dao.common.BaseDao;
import by.megumin.entity.orderEntity.Order;
import by.megumin.entity.orderEntity.OrderContent;

import java.util.List;

public interface OrderContentDao extends BaseDao<OrderContent> {
    List<OrderContent> getByOrder(Order order);
}

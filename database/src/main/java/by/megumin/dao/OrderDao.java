package by.megumin.dao;

import by.megumin.dao.common.BaseDao;
import by.megumin.entity.orderEntity.Order;
import by.megumin.entity.userEntity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderDao extends BaseDao<Order> {
    List<Order> getByUser(User owner);
}

package by.megumin.service;

import by.megumin.dao.OrderContentDao;
import by.megumin.dao.common.BaseDao;
import by.megumin.entity.orderEntity.Order;
import by.megumin.entity.orderEntity.OrderContent;
import by.megumin.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderContentServiceImpl extends BaseServiceImpl<OrderContent> implements OrderContentService {

    @Autowired
    private OrderContentDao orderContentDao;

    @Override
    public List<OrderContent> getByOrder(Order order) {
        return orderContentDao.getByOrder(order);
    }

    @Override
    protected BaseDao<OrderContent> getDao() {
        return orderContentDao;
    }
}
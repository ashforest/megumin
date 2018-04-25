package by.megumin.dao;

import by.megumin.dao.common.BaseDao;
import by.megumin.dao.common.BaseDaoTest;
import by.megumin.entity.orderEntity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDetailDaoTest extends BaseDaoTest<OrderDetail> {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Override
    protected BaseDao<OrderDetail> getDao() {
        return orderDetailDao;
    }

    @Override
    protected OrderDetail getModel() {
        return new OrderDetail();
    }
}

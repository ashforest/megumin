package by.megumin.dao;

import by.megumin.dao.common.BaseDao;
import by.megumin.dao.common.BaseDaoTest;
import by.megumin.entity.orderEntity.Order;
import by.megumin.entity.orderEntity.OrderContent;
import by.megumin.entity.userEntity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

public class OrderContentDaoTest extends BaseDaoTest<OrderContent> {

    @Autowired
    private OrderContentDao orderContentDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void getByOrderTest() throws Exception {
        getDataImporter().importData();
        User user = userDao.getByLogin("mivan");
        List<Order> orders = orderDao.getByUser(user);
        List<OrderContent> contents = orderContentDao.getByOrder(orders.get(0));
        assertThat(contents, hasSize(1));
    }

    @Override
    protected BaseDao<OrderContent> getDao() {
        return orderContentDao;
    }

    @Override
    protected OrderContent getModel() {
        return new OrderContent();
    }
}
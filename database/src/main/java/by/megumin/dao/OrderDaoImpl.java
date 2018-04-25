package by.megumin.dao;

import by.megumin.dao.common.BaseDaoImpl;
import by.megumin.entity.orderEntity.Order;
import by.megumin.entity.orderEntity.OrderDetail;
import by.megumin.entity.userEntity.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao {

    @Override
    public List<Order> getByUser(User owner) {
        List<Order> orders = getSessionFactory().getCurrentSession()
                .createQuery("select o from Order o where o.owner.id=:id", Order.class)
                .setParameter("id", owner.getId())
                .getResultList();
        return orders;
    }
}

package by.megumin.dao;

import by.megumin.dao.common.BaseDaoImpl;
import by.megumin.entity.orderEntity.Order;
import by.megumin.entity.orderEntity.OrderContent;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrderContentDaoImpl extends BaseDaoImpl<OrderContent> implements OrderContentDao {
    @Override
    public List<OrderContent> getByOrder(Order order) {
        CriteriaBuilder cb = getSessionFactory().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<OrderContent> criteria = cb.createQuery(OrderContent.class);
        Root<OrderContent> orderContent = criteria.from(OrderContent.class);
        Path<Object> orderPath = orderContent.get("order");
        criteria.select(orderContent)
                .where(cb.equal(orderPath.get("id"), order.getId()));
        return getSessionFactory().getCurrentSession().createQuery(criteria).getResultList();
    }
}
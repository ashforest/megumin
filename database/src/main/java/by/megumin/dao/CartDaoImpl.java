package by.megumin.dao;

import by.megumin.dao.common.BaseDaoImpl;
import by.megumin.entity.orderEntity.Cart;
import by.megumin.entity.productEntity.Product;
import by.megumin.entity.userEntity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CartDaoImpl extends BaseDaoImpl<Cart> implements CartDao {

    @Override
    public List<Cart> getByUser(User user) {
        CriteriaBuilder cb = getSessionFactory().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Cart> criteria = cb.createQuery(Cart.class);
        Root<Cart> cart = criteria.from(Cart.class);
        Path<Object> owner = cart.get("owner");
        criteria.select(cart)
                .where(cb.equal(owner.get("id"), user.getId()));
        return getSessionFactory().getCurrentSession().createQuery(criteria).getResultList();
    }

    @Override
    public Cart getCart(User user, Product product) {
        Cart cart = getSessionFactory().getCurrentSession()
                .createQuery("select c from Cart c where c.owner.id=:userId and c.product.id=:productId", Cart.class)
                .setParameter("userId", user.getId())
                .setParameter("productId", product.getId())
                .getResultList().get(0);
        return cart;
    }

    @Override
    public Integer getCountProductsInCart(User user) {
        Long count = getSessionFactory().getCurrentSession()
                .createQuery("select sum(c.amount) from Cart c where c.owner.id=:id", Long.class)
                .setParameter("id", user.getId())
                .uniqueResult();
        return count == null ? 0 : Math.toIntExact(count);
    }

    @Override
    public void cleanByUser(User user) {
        getSessionFactory().getCurrentSession()
                .createQuery("delete from Cart c where c.owner.id=:id")
                .setParameter("id", user.getId()).executeUpdate();
    }
}
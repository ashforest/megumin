package by.megumin.dao;

import by.megumin.dao.common.BaseDao;
import by.megumin.entity.orderEntity.Cart;
import by.megumin.entity.productEntity.Product;
import by.megumin.entity.userEntity.User;

import java.util.List;


public interface CartDao extends BaseDao<Cart> {
    List<Cart> getByUser(User user);
    Cart getCart(User user, Product product);
    Integer getCountProductsInCart(User user);
    void cleanByUser(User user);
}

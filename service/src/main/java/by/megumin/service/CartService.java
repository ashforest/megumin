package by.megumin.service;

import by.megumin.entity.orderEntity.Cart;
import by.megumin.entity.productEntity.Product;
import by.megumin.entity.userEntity.User;
import by.megumin.service.common.BaseService;

import java.util.List;

public interface CartService extends BaseService<Cart> {
    List<Cart> getByUser(User user);
    void deleteProductFromCart(User user, Product product, Integer amount);
    void addToCart(User user, Product product, Integer amount);
    Integer getCountProductsInCart(User user);
    void cleanByUser(User user);
}

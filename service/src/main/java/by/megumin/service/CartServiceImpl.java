package by.megumin.service;

import by.megumin.dao.CartDao;
import by.megumin.dao.ProductDao;
import by.megumin.dao.common.BaseDao;
import by.megumin.entity.orderEntity.Cart;
import by.megumin.entity.productEntity.Product;
import by.megumin.entity.userEntity.User;
import by.megumin.service.common.BaseServiceImpl;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Log4j
public class CartServiceImpl extends BaseServiceImpl<Cart> implements CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Cart> getByUser(User user) {
        return cartDao.getByUser(user);
    }

    @Override
    public void deleteProductFromCart(User user, Product product, Integer amount) {
        Cart cart = cartDao.getCart(user, product);
        Integer currentAmount = cart.getAmount();

        if(amount == currentAmount) {
            cartDao.delete(cart);
        } else if(amount < currentAmount) {
            cart.setAmount(currentAmount - amount);
            cartDao.update(cart);
        } else {
            throw new IllegalStateException("Incorrect amount of product to be removed");
        }
    }

    @Override
    public void addToCart(User user, Product product, Integer amount) {
        List<Cart> carts = cartDao.getByUser(user);
        boolean isProductPresent = false;
        product = productDao.getByID(product.getId());
        for(Cart cart : carts) {
            if(cart.getProduct().getId() == product.getId()){
                isProductPresent = true;
                Integer currentAmount = cart.getAmount();
                cart.setAmount(currentAmount + amount);
                cartDao.update(cart);
                break;
            }
        }
        if(!isProductPresent) {
            Cart cart = new Cart();
            cart.setAmount(amount);
            cart.setProduct(product);
            cart.setOwner(user);
            cartDao.save(cart);
        }
    }

    @Override
    public Integer getCountProductsInCart(User user) {
        return cartDao.getCountProductsInCart(user);
    }

    @Override
    public void cleanByUser(User user) {
        cartDao.cleanByUser(user);
    }

    @Override
    protected BaseDao<Cart> getDao() {
        return cartDao;
    }
}
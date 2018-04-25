package by.megumin.service;

import by.megumin.entity.otherEntity.Review;
import by.megumin.entity.productEntity.Product;
import by.megumin.entity.userEntity.User;
import by.megumin.service.common.BaseService;

import java.util.List;

public interface ReviewService extends BaseService<Review> {
    List<Review> getByProduct(Product product);
    Review create(String content, Long userID, Long productID);
}

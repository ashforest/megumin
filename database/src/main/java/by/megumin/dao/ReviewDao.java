package by.megumin.dao;

import by.megumin.dao.common.BaseDao;
import by.megumin.entity.otherEntity.Review;
import by.megumin.entity.productEntity.Product;

import java.util.List;

public interface ReviewDao extends BaseDao<Review> {
    List<Review> getByProduct(Product product);
}
package by.megumin.service;

import by.megumin.dao.ReviewDao;
import by.megumin.dao.common.BaseDao;
import by.megumin.entity.otherEntity.Review;
import by.megumin.entity.productEntity.Product;
import by.megumin.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl extends BaseServiceImpl<Review> implements ReviewService {
    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Override
    public List<Review> getByProduct(Product product) {
        return reviewDao.getByProduct(product);
    }

    @Override
    public Review create(String content, Long userID, Long productID) {
        Review review = new Review();
        review.setDateOfCreation(LocalDateTime.now());
        review.setContent(content);
        review.setProduct(productService.getByID(productID));
        review.setOwner(userService.getByID(userID));
        return review;
    }

    @Override
    protected BaseDao<Review> getDao() {
        return reviewDao;
    }
}

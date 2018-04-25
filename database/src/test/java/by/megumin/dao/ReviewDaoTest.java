package by.megumin.dao;

import by.megumin.config.TestConfig;
import by.megumin.dao.common.BaseDao;
import by.megumin.dao.common.BaseDaoTest;
import by.megumin.entity.productEntity.Product;
import by.megumin.entity.otherEntity.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

public class ReviewDaoTest extends BaseDaoTest<Review> {

    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private ProductDao productDao;

    @Override
    protected BaseDao<Review> getDao() {
        return reviewDao;
    }

    @Override
    protected Review getModel() {
        return new Review();
    }

    @Test
    public void testGetByProduct() {
        getDataImporter().importData();
        Product product = productDao.getByCategoryName("Мобильные телефоны", 1, 10).get(0);
        System.out.println("PRODUCT: " + product);

        List<Review> reviews = getReviews(reviewDao, product);

        assertThat(reviews, hasSize(1));
        assertThat(reviews.get(0).getContent(), is("Very good!"));
    }

    private List<Review> getReviews (ReviewDao reviewDao, Product product) {
        return reviewDao.getByProduct(product);
    }
}
package by.megumin.dao;

import by.megumin.config.TestConfig;
import by.megumin.dao.common.BaseDao;
import by.megumin.dao.common.BaseDaoTest;
import by.megumin.entity.productEntity.Category;
import by.megumin.entity.productEntity.Detail;
import by.megumin.entity.productEntity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ProductDaoTest extends BaseDaoTest<Product> {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private DetailDao detailDao;

    @Autowired
    private CategoryDao categoryDao;

    @Override
    protected BaseDao<Product> getDao() {
        return productDao;
    }

    @Override
    protected Product getModel() {
        return new Product();
    }

    @Test
    public void testGetByCategoryNameTest() {
        getDataImporter().importData();
        List<Product> products = productDao.getByCategoryName("Мобильные телефоны", 1, 10);

        assertThat(products, hasSize(1));
        assertThat(products.get(0).getName(), is("Xiaomi Redmi 3"));
    }

    @Test
    public void getTotalPageTest() throws Exception {
        getDataImporter().importData();
        Category category = categoryDao.getByID(1L);
        Integer page = productDao.getTotalPage(category, 1);
        System.out.println("PAGE: " + page);
        assertThat(page, is(1));
    }

    @Test
    public void getNextImageNumberTest() throws Exception {
        getDataImporter().importData();
        Integer number = productDao.getNextImageNumber();
        assertThat(number, is(3));
    }

    @Test
    public void getWithFilterTest() throws Exception {
        getDataImporter().importData();
        List<String> list = Arrays.asList("Android");
        Map<Long, List<String>> map = new HashMap<>();
        Detail detail = detailDao.getByName("Операционная система");
        detailDao.save(detail);
        map.put(1L, list);
        List<Long> productsIds = productDao.getWithFilter(map, 1L, 1, 10);
        assertThat(productsIds, hasSize(0));
    }

    @Test
    public void getTotalPageWithFilterTest() throws Exception {
        getDataImporter().importData();
        List<String> list = Arrays.asList("Android");
        Map<Long, List<String>> map = new HashMap<>();
        Detail detail = detailDao.getByName("Операционная система");
        detailDao.save(detail);
        map.put(1L, list);
        Integer totalPageWithFilter = productDao.getTotalPageWithFilter(map, 1L, 10);
        assertThat(totalPageWithFilter, is(0));
    }
}
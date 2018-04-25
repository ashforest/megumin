package by.megumin.dao;

import by.megumin.config.TestConfig;
import by.megumin.dao.common.BaseDao;
import by.megumin.dao.common.BaseDaoTest;
import by.megumin.entity.productEntity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CategoryDaoTest extends BaseDaoTest<Category> {

    @Autowired
    private CategoryDao dao;

    @Override
    protected BaseDao<Category> getDao() {
        return dao;
    }

    @Override
    protected Category getModel() {
        return new Category();
    }
}
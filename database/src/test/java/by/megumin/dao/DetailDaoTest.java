package by.megumin.dao;

import by.megumin.config.TestConfig;
import by.megumin.dao.common.BaseDao;
import by.megumin.dao.common.BaseDaoTest;
import by.megumin.entity.productEntity.Detail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DetailDaoTest extends BaseDaoTest<Detail> {

    @Autowired
    private DetailDao dao;

    @Override
    protected BaseDao<Detail> getDao() {
        return dao;
    }

    @Override
    protected Detail getModel() {
        return new Detail();
    }

    @Test
    public void testGetByName() {
        getDataImporter().importData();
        Detail detail = dao.getByName("Год выпуска");
        assertThat(detail.getName(), is("Год выпуска"));
    }
}
package by.megumin.dao;

import by.megumin.config.TestConfig;
import by.megumin.dao.common.BaseDao;
import by.megumin.dao.common.BaseDaoTest;
import by.megumin.entity.productEntity.Characteristic;
import by.megumin.entity.productEntity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

public class CharacteristicDaoTest extends BaseDaoTest<Characteristic> {

    @Autowired
    private CharacteristicDao characteristicDao;

    @Autowired
    private ProductDao productDao;

    @Test
    public void getByProduct() throws Exception {
        getDataImporter().importData();
        Product product = productDao.getByID(1L);
        List<Characteristic> characteristics = characteristicDao.getByProduct(product);
        assertThat(characteristics, hasSize(4));

    }

    @Override
    protected BaseDao<Characteristic> getDao() {
        return characteristicDao;
    }

    @Override
    protected Characteristic getModel() {
        return new Characteristic();
    }
}
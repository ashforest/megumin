package by.megumin.dao.common;

import by.megumin.config.TestConfig;
import by.megumin.entity.otherEntity.BaseEntity;
import by.megumin.util.DataImporter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class BaseDaoTest<T extends BaseEntity> {

    protected abstract BaseDao<T> getDao();
    protected abstract T getModel();

    private DataImporter dataImporter;

    @Autowired
    public void setDataImporter(DataImporter dataImporter) {
        this.dataImporter = dataImporter;
    }

    public DataImporter getDataImporter() {
        return dataImporter;
    }

    @Test
    public void getByIdTest() {
        T model = getModel();
        getDao().save(model);
        T result = getDao().getByID(model.getId());
        assertNotNull(result);
    }

    @Test
    public void saveTest() {
        T model = getModel();
        getDao().save(model);
        T result = getDao().getByID(model.getId());
        assertNotNull(result);
    }

    @Test
    public void deleteTest() {
        T model = getModel();
        getDao().save(model);
        Long id = model.getId();
        getDao().delete(model);
        T result = getDao().getByID(id);
        assertNull(result);
    }

    @Test
    public void updateTest() {
        T model = getModel();
        getDao().save(model);
        getDao().update(model);
        T result = getDao().getByID(model.getId());
        assertNotNull(result);
    }

    @Test
    public void findAllTest() {
        List<T> list = getDao().findAll();
        assertNotNull(list);
    }
}
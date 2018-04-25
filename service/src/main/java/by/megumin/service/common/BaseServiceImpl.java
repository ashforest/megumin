package by.megumin.service.common;

import by.megumin.dao.common.BaseDao;
import by.megumin.entity.otherEntity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T>{

    protected abstract BaseDao<T> getDao();

    @Override
    public Long save(T entity) {
        return getDao().save(entity);
    }

    @Override
    public T getByID(Long id) {
        return getDao().getByID(id);
    }

    @Override
    public void update(T entity) {
        getDao().update(entity);
    }

    @Override
    public void delete(T entity) {
        getDao().delete(entity);
    }

    @Override
    public List<T> findAll() {
        return getDao().findAll();
    }
}
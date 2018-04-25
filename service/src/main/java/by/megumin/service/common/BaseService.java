package by.megumin.service.common;

import by.megumin.entity.otherEntity.BaseEntity;
import by.megumin.entity.userEntity.User;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    Long save(T entity);
    T getByID(Long id);
    void update(T entity);
    void delete(T entity);
    List<T> findAll();
}

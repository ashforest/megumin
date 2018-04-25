package by.megumin.dao.common;

import by.megumin.entity.otherEntity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T> {

    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> entityClass;

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        this.entityClass =
                (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), BaseDaoImpl.class);
    }

    @Override
    public T getByID(Long id) {
        Session session = sessionFactory.getCurrentSession();
        T entity = session.get(entityClass, id);
        return entity;
    }

    @Override
    public Long save(T entity) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(entity);
        return id;
    }

    @Override
    public void update(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
    }

    @Override
    public void delete(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(entity);
    }

    @Override
    public List<T> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(entityClass);
        query.select(query.from(entityClass));
        List<T> resultList = session.createQuery(query).getResultList();
        return resultList;
    }
}
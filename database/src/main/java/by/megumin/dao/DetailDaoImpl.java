package by.megumin.dao;

import by.megumin.dao.common.BaseDaoImpl;
import by.megumin.entity.productEntity.Detail;
import org.springframework.stereotype.Repository;

@Repository
public class DetailDaoImpl extends BaseDaoImpl<Detail> implements DetailDao {

    public Detail getByName(String name) {
        Detail detail = getSessionFactory().getCurrentSession().
                createQuery("select d from Detail d where d.name=:name", Detail.class)
                .setParameter("name", name)
                .getResultList()
                .get(0);

        return detail;
    }
}
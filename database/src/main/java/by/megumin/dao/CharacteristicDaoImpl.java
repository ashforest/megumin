package by.megumin.dao;

import by.megumin.dao.common.BaseDaoImpl;
import by.megumin.entity.productEntity.Characteristic;
import by.megumin.entity.productEntity.Detail;
import by.megumin.entity.productEntity.Product;
import org.springframework.stereotype.Repository;
import sun.awt.SunHints;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

@Repository
public class CharacteristicDaoImpl extends BaseDaoImpl<Characteristic> implements CharacteristicDao {
    @Override
    public List<Characteristic> getByProduct(Product product) {
        CriteriaBuilder cb = getSessionFactory().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Characteristic> criteria = cb.createQuery(Characteristic.class);
        Root<Characteristic> characteristic = criteria.from(Characteristic.class);
        Path<Object> productPath = characteristic.get("product");
        criteria.select(characteristic)
                .where(cb.equal(productPath.get("id"), product.getId()));
        return getSessionFactory().getCurrentSession().createQuery(criteria).getResultList();
    }
}
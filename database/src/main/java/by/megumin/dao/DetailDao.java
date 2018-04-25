package by.megumin.dao;

import by.megumin.dao.common.BaseDao;
import by.megumin.entity.productEntity.Detail;

public interface DetailDao extends BaseDao<Detail> {
    Detail getByName(String name);
}

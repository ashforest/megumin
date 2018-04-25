package by.megumin.dao;

import by.megumin.dao.common.BaseDao;
import by.megumin.entity.productEntity.Characteristic;
import by.megumin.entity.productEntity.Detail;
import by.megumin.entity.productEntity.Product;

import java.util.List;

public interface CharacteristicDao extends BaseDao<Characteristic> {
    List<Characteristic> getByProduct (Product product);
}

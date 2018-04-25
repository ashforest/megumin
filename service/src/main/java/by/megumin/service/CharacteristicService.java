package by.megumin.service;

import by.megumin.entity.productEntity.Characteristic;
import by.megumin.entity.productEntity.Detail;
import by.megumin.entity.productEntity.Product;
import by.megumin.service.common.BaseService;

import java.util.List;

public interface CharacteristicService extends BaseService<Characteristic> {
    List<Characteristic> getByProduct(Product product);
}

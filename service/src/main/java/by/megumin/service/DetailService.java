package by.megumin.service;

import by.megumin.entity.productEntity.Detail;
import by.megumin.service.common.BaseService;

public interface DetailService extends BaseService<Detail> {
    Detail getByName(String name);
}

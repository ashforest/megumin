package by.megumin.service;

import by.megumin.dao.CharacteristicDao;
import by.megumin.dao.common.BaseDao;
import by.megumin.entity.productEntity.Characteristic;
import by.megumin.entity.productEntity.Detail;
import by.megumin.entity.productEntity.Product;
import by.megumin.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CharacteristicServiceImpl extends BaseServiceImpl<Characteristic> implements CharacteristicService {
    @Autowired
    private CharacteristicDao characteristicDao;

    @Override
    public List<Characteristic> getByProduct(Product product) {
        return characteristicDao.getByProduct(product);
    }

    @Override
    protected BaseDao<Characteristic> getDao() {
        return characteristicDao;
    }
}
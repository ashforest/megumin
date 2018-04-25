package by.megumin.service;

import by.megumin.dao.DetailDao;
import by.megumin.dao.common.BaseDao;
import by.megumin.entity.productEntity.Detail;
import by.megumin.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class DetailServiceImpl extends BaseServiceImpl<Detail> implements DetailService {

    @Autowired
    private DetailDao detailDao;

    @Override
    public Detail getByName(String name) {
        return detailDao.getByName(name);
    }

    @Override
    protected BaseDao<Detail> getDao() {
        return detailDao;
    }
}

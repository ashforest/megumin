package by.megumin.service;

import by.megumin.dao.CategoryDao;
import by.megumin.dao.common.BaseDao;
import by.megumin.entity.productEntity.Category;
import by.megumin.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    protected BaseDao<Category> getDao() {
        return categoryDao;
    }
}

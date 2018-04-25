package by.megumin.dao;

import by.megumin.dao.common.BaseDao;
import by.megumin.entity.productEntity.Category;
import by.megumin.entity.productEntity.Characteristic;
import by.megumin.entity.productEntity.Product;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductDao extends BaseDao<Product> {
    List<Product> getByCategoryName(String categoryName, int pageNumber, int countProductInPage);
    Integer getTotalPage(Category category, int countProductInPage);
    Integer getNextImageNumber();
    List<Long> getWithFilter(Map<Long, List<String>> detailValueMap, Long categoryId, int pageNumber, int countProductInPage);
    Integer getTotalPageWithFilter(Map<Long, List<String>> detailValueMap, Long categoryId, int countProductInPage);
}

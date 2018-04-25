package by.megumin.service;

import by.megumin.dto.FilterDto;
import by.megumin.entity.productEntity.Category;
import by.megumin.entity.productEntity.Characteristic;
import by.megumin.entity.productEntity.Detail;
import by.megumin.entity.productEntity.Product;
import by.megumin.service.common.BaseService;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface ProductService  extends BaseService<Product> {
    List<Product> getByCategoryName(String name, int pageNumber);
    Integer getTotalPage(Category category);
    Integer getNextImageNumber();
    List<Product> getByFilter(FilterDto filterDto, int pageNumber);
    Integer getTotalPageWithFilter(FilterDto filterDto);
}
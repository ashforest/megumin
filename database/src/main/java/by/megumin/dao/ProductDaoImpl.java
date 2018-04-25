package by.megumin.dao;

import by.megumin.dao.common.BaseDaoImpl;
import by.megumin.entity.productEntity.Category;
import by.megumin.entity.productEntity.Product;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {

    @Override
    public List<Product> getByCategoryName(String categoryName, int pageNumber, int countProductInPage) {
        List<Product> products = getSessionFactory().getCurrentSession()
                .createQuery("select p from Product p where p.category.name=:name", Product.class)
                .setParameter("name", categoryName)
                .setFirstResult(countProductInPage * ((pageNumber - 2) + 1))
                .setMaxResults(countProductInPage)
                .getResultList();
        return products;
    }

    @Override
    public Integer getTotalPage(Category category, int countProductInPage) {
        Long countProducts = getSessionFactory().getCurrentSession()
                .createQuery("select count(p) from Product p where p.category.id=:id", Long.class)
                .setParameter("id", category.getId())
                .getSingleResult();
        int pageCount = Math.toIntExact(countProducts) / countProductInPage;
        int pages = Math.toIntExact(countProducts) % countProductInPage;
        return pages == 0 ? (pageCount) : (pageCount + 1);
    }

    @Override
    public Integer getNextImageNumber() {
        List<Product> products = getSessionFactory().getCurrentSession()
                .createQuery("select p from Product p where p.image is not null order by p.id desc", Product.class)
                .setMaxResults(1)
                .getResultList();
        if(products.size() == 0) {
            return 1;
        } else {
            String image = products.get(products.size() - 1).getImage();
            String[] split = image.split("\\.");
            return Integer.valueOf(split[0]) + 1;
        }
    }

    @Override
    public List<Long> getWithFilter(Map<Long, List<String>> detailValueMap, Long categoryId, int pageNumber, int countProductInPage) {

        StringBuilder nativeQuery = new StringBuilder("select p.id as id from products p " +
                "LEFT JOIN characteristics c1 ON p.id=c1.product_id AND c1.detail_id=1 " +
                "LEFT JOIN characteristics c2 ON p.id=c2.product_id AND c2.detail_id=2 " +
                "LEFT JOIN characteristics c3 ON p.id=c3.product_id AND c3.detail_id=3 " +
                "LEFT JOIN characteristics c4 ON p.id=c4.product_id AND c4.detail_id=4 " +
                "LEFT JOIN characteristics c5 ON p.id=c5.product_id AND c5.detail_id=5 " +
                "WHERE ");
        nativeQuery.append("p.category_id = ");
        nativeQuery.append(String.valueOf(categoryId));
        nativeQuery.append(" AND ");

        List<String> forAddingToQuery = new ArrayList<>();
        for(Map.Entry<Long, List<String>> entry : detailValueMap.entrySet()) {
            if(entry.getKey() == 4L) {
                if(entry.getValue().size() == 2) {
                    forAddingToQuery.add("c4.value >= " + entry.getValue().get(0));
                    forAddingToQuery.add("c4.value <= " + entry.getValue().get(1));
                } else {
                    if(entry.getValue().get(0).split(":")[0].equals("FROM")) {
                        forAddingToQuery.add("c4.value >= " + entry.getValue().get(0).split(":")[1]);
                    }
                    if(entry.getValue().get(0).split(":")[0].equals("TO")) {
                        forAddingToQuery.add("c4.value <= " + entry.getValue().get(0).split(":")[1]);
                    }
                }
            } else {
                String num = String.valueOf(entry.getKey());
                forAddingToQuery.add("c" + num + ".value IN(?" + num +")");
            }
        }
        String toAdding = String.join(" AND ", forAddingToQuery);

        String fullNativeQuery = nativeQuery + toAdding +
                " LIMIT " + countProductInPage + " OFFSET "  + countProductInPage * ((pageNumber - 2) + 1);

        NativeQuery query = getSessionFactory().getCurrentSession().createNativeQuery(fullNativeQuery);

        if(detailValueMap.containsKey(1L)) {
            query.setParameter(1, detailValueMap.get(1L));
        }
        if(detailValueMap.containsKey(3L)) {
            query.setParameter(3, detailValueMap.get(3L));
        }
        if(detailValueMap.containsKey(5L)) {
            query.setParameter(5, detailValueMap.get(5L));
        }

        return (List<Long>) query.list().stream().map(i -> Long.valueOf(Objects.toString(i))).collect(Collectors.toList());
    }

    @Override
    public Integer getTotalPageWithFilter(Map<Long, List<String>> detailValueMap, Long categoryId, int countProductInPage) {
        int productCount = getWithFilter(detailValueMap, categoryId, 1, 100).size();
        return productCount % countProductInPage == 0 ? productCount / countProductInPage : productCount / countProductInPage + 1;
    }
}
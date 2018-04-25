package by.megumin.dto;

import by.megumin.entity.productEntity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FilterDto {
    private Category category;
    private List<Integer> years;
    private Integer yearFrom;
    private Integer yearTo;
    private String priceFrom;
    private String priceTo;
    private List<String> os;
    private List<String> producers;
}

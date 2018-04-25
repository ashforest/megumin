package by.megumin.entity.productEntity;

import by.megumin.entity.otherEntity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "characteristics")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Characteristic extends BaseEntity {
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "detail_id")
    private Detail detail;

    @Column(name = "value")
    private String value;
}

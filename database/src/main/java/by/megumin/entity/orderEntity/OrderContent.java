package by.megumin.entity.orderEntity;

import by.megumin.entity.otherEntity.BaseEntity;
import by.megumin.entity.productEntity.Product;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@Table(name = "order_content")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderContent extends BaseEntity {

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "amount")
    private Integer amount;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;
}

package by.megumin.entity.otherEntity;

import by.megumin.entity.productEntity.Product;
import by.megumin.entity.userEntity.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Data
public class Review extends BaseEntity {
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User owner;

    @Column(name = "date")
    private LocalDateTime dateOfCreation;

    @Column(name = "content")
    private String content;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "product_id")
    private Product product;
}
package by.megumin.entity.orderEntity;

import by.megumin.entity.otherEntity.BaseEntity;
import by.megumin.entity.userEntity.User;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Order extends BaseEntity {
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "status")
    private OrderStatus status;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "detail_id")
    private OrderDetail detail;
}

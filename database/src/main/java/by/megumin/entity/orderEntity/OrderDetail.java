package by.megumin.entity.orderEntity;

import by.megumin.entity.otherEntity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetail extends BaseEntity {

    @Column(name = "date_of_reception")
    private LocalDateTime dateOfReceipt;

    @Column(name = "closing_date")
    private LocalDateTime closingDate;
}

package by.megumin.entity.orderEntity;

import by.megumin.entity.otherEntity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Embeddable
@Entity
@Table(name = "payment_details")
public class PaymentDetails extends BaseEntity {

    public PaymentDetails(PaymentType paymentType, Integer repaymentMonthTerm) {
        this.paymentType = paymentType;
        this.repaymentMonthTerm = repaymentMonthTerm;
    }

    public PaymentDetails(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "term")
    private Integer repaymentMonthTerm;

    @Column(name = "interest_rate")
    private Double interestRate;

}

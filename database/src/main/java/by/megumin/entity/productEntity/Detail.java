package by.megumin.entity.productEntity;

import by.megumin.entity.otherEntity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "details")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Detail extends BaseEntity {
    @Column(name = "name")
    private String name;
}

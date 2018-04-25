package by.megumin.entity.otherEntity;

import by.megumin.entity.userEntity.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "promotions")
@Data
public class Promotion extends BaseEntity {
    @Column(name = "date_of_creation")
    private LocalDateTime dateOfCreation;

    @Column(name = "closing_date")
    private LocalDateTime closingDate;

    @Column(name = "sale")
    private Integer sale;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(name = "promotions_users",
            joinColumns = @JoinColumn(name = "promotion_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> clients = new HashSet<>();
}
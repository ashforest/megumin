package by.megumin.entity.userEntity;

import by.megumin.entity.otherEntity.Address;
import by.megumin.entity.otherEntity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile extends BaseEntity {
    @Column(name = "telephone")
    private String telephone;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated
    private Address address;

    @Column(name = "email")
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}

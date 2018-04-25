package by.megumin.entity.userEntity;

import by.megumin.entity.otherEntity.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {

    @Column(name = "user")
    @NotEmpty(message = "user.validation.name.not_empty")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
}
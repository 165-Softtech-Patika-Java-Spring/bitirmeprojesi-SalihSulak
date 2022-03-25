package com.softtech.marketapi.entity;

import com.softtech.marketapi.generic.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "USER_ID_SEQ", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Pattern(regexp="^[A-Za-z ğüışç]*$",message = "Invalid Name")
    @Column(nullable = false,unique = true)
    private String name;

    @Pattern(regexp="^[A-Za-z ğüışç]*$",message = "Invalid Surname")
    @Column(nullable = false)
    private String surname;

    @Pattern(regexp="^[a-z_.0-9]*$",message = "Invalid Username")
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;


}

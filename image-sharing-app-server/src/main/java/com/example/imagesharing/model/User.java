package com.example.imagesharing.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) // jpa requires no-args constructor
@Getter
@Setter
@ToString
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
            "username"
    }, name = "UK_USERNAME"),
    @UniqueConstraint(columnNames = {
            "email"
    }, name = "UK_EMAIL")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    public User(String username, String password, String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}

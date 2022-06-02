package com.searchbar.sweng.searchbar.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
    /**
     * The User class is used for the UserRegisteredEvent.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    @Version
    private int version;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

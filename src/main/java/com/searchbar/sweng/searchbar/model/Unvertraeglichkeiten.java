package com.searchbar.sweng.searchbar.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "unvertraeglichkeiten")
@Entity
public class Unvertraeglichkeiten {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean histamine;
    private boolean fructose;
    private boolean lactose;

    public Unvertraeglichkeiten(boolean histamine, boolean fructose, boolean lactose) {
        this.histamine = histamine;
        this.fructose = fructose;
        this.lactose = lactose;
    }
}

package com.searchbar.sweng.searchbar.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Unvertraeglichkeiten {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uId;
    private boolean histamine;
    private boolean fructose;
    private boolean lactose;

    public Unvertraeglichkeiten(boolean histamine, boolean fructose, boolean lactose) {
        this.histamine = histamine;
        this.fructose = fructose;
        this.lactose = lactose;
    }
}

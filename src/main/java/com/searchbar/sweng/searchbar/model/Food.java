package com.searchbar.sweng.searchbar.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private int id;
    private String name;
    private int proteine;
    private int kalorien;
    private String menge;

    public Food(int id,String name, int proteine, int kalorien,String menge){
        this.id = id;
        this.name = name;
        this.proteine = proteine;
        this.kalorien = kalorien;
        this.menge = menge;
    }
    public Food(String name, int proteine, int kalorien, String menge){
        this.name = name;
        this.proteine = proteine;
        this.kalorien = kalorien;
        this.menge = menge;
    }
}

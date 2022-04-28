package com.searchbar.sweng.searchbar.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.*;


@Getter
@Setter
@NoArgsConstructor
@Table(name = "rezepte")
@Entity
public class Rezepte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(orphanRemoval = true,
                cascade = CascadeType.ALL)
    private List<Food> foods;
    private int arbeitszeit;
    private int kochzeit;
    private int gesamtzeit;
    private int portionen;
    @Enumerated(EnumType.STRING)
    private Menueart menueart;
    private boolean isVegan;
    private boolean isVegetarisch;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unvertraeglichkeiten_id")
    Unvertraeglichkeiten unvertraeglichkeiten;



    public Rezepte(int id,String rezeptName, int arbeitszeit, int kochzeit,int gesamtzeit, int portionen, Menueart menueart, boolean isVegan, boolean isVegetarisch,List<Food> foods,Unvertraeglichkeiten uv){
        this.id = id;
        this.name = rezeptName;
        this.arbeitszeit = arbeitszeit;
        this.kochzeit = kochzeit;
        this.gesamtzeit = gesamtzeit;
        this.portionen = portionen;
        this.menueart = menueart;
        this.isVegan = isVegan;
        this.isVegetarisch = isVegetarisch;
        this.foods = foods;
        this.unvertraeglichkeiten = uv;
    }
    public Rezepte(String rezeptName, int arbeitszeit, int kochzeit,int gesamtzeit, int portionen, Menueart menueart, boolean isVegan, boolean isVegetarisch,List<Food> foods,Unvertraeglichkeiten uv){
        this.name = rezeptName;
        this.arbeitszeit = arbeitszeit;
        this.kochzeit = kochzeit;
        this.gesamtzeit = gesamtzeit;
        this.portionen = portionen;
        this.menueart = menueart;
        this.isVegan = isVegan;
        this.isVegetarisch = isVegetarisch;
        this.foods = foods;
        this.unvertraeglichkeiten = uv;
    }
}

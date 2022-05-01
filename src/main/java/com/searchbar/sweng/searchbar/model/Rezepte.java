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
    @Column(name = "rezepte_id", updatable = false, nullable = false)
    private int id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "rezepte_foods",
                joinColumns = @JoinColumn(name = "rezepte_id"),
                inverseJoinColumns = @JoinColumn(name = "food_id"))
    private List<Food> foods = new ArrayList<>();
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
    private Unvertraeglichkeiten unvertraeglichkeiten;


    public int getCalories(){
        int sum = 0;
        for(Food f:this.foods){
            sum = sum+f.getKalorien();
        }
        return sum;
    }
    public int getProteins(){
        int sum = 0;
        for(Food f:this.foods){
            sum = sum+f.getProteine();
        }
        return sum;
    }
    public Rezepte(int id,String rezeptName, int arbeitszeit, int kochzeit,int gesamtzeit, int portionen, Menueart menueart,
                   boolean isVegan, boolean isVegetarisch,List<Food> foods,Unvertraeglichkeiten uv){
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
    public Rezepte(String rezeptName, int arbeitszeit, int kochzeit,int gesamtzeit, int portionen, Menueart menueart,
                   boolean isVegan, boolean isVegetarisch,List<Food> foods,Unvertraeglichkeiten uv){
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

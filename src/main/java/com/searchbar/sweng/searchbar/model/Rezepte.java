package com.searchbar.sweng.searchbar.model;

import lombok.*;

import javax.persistence.*;

import java.util.*;
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rezepte {
    /**
     * Most important class, stores all the information a recipe has and offers helping methods to other classes.
     */
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
    private int portionen;
    @Enumerated(EnumType.STRING)
    private Menueart menueart;
    private boolean isVegan;
    private boolean isVegetarisch;
    private String author;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unvertraeglichkeiten_id")
    private Unvertraeglichkeiten unvertraeglichkeiten;
    @Lob
    private String image;
    @Version
    private int version;

    public int getGesamtzeit(){
        return getArbeitszeit()+getKochzeit();
    }
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

    public Rezepte(String author,String rezeptName, int arbeitszeit, int kochzeit, int portionen, Menueart menueart,
                   boolean isVegan, boolean isVegetarisch,List<Food> foods,Unvertraeglichkeiten uv,String image){
        this.author = author;
        this.name = rezeptName;
        this.arbeitszeit = arbeitszeit;
        this.kochzeit = kochzeit;
        this.portionen = portionen;
        this.menueart = menueart;
        this.isVegan = isVegan;
        this.isVegetarisch = isVegetarisch;
        this.foods = foods;
        this.unvertraeglichkeiten = uv;
        this.image = image;
    }
    public Rezepte(String author,String rezeptName, int arbeitszeit, int kochzeit, int portionen, Menueart menueart,
                   boolean isVegan, boolean isVegetarisch,List<Food> foods,Unvertraeglichkeiten uv){
        this.author = author;
        this.name = rezeptName;
        this.arbeitszeit = arbeitszeit;
        this.kochzeit = kochzeit;
        this.portionen = portionen;
        this.menueart = menueart;
        this.isVegan = isVegan;
        this.isVegetarisch = isVegetarisch;
        this.foods = foods;
        this.unvertraeglichkeiten = uv;
    }
    public Rezepte(String author,int id, String rezeptName, int arbeitszeit, int kochzeit, int portionen, Menueart menueart,
                   boolean isVegan, boolean isVegetarisch,List<Food> foods,Unvertraeglichkeiten uv){
        this.author = author;
        this.id = id;
        this.name = rezeptName;
        this.arbeitszeit = arbeitszeit;
        this.kochzeit = kochzeit;
        this.portionen = portionen;
        this.menueart = menueart;
        this.isVegan = isVegan;
        this.isVegetarisch = isVegetarisch;
        this.foods = foods;
        this.unvertraeglichkeiten = uv;
    }
    public Rezepte(String author,int id, String rezeptName, int arbeitszeit, int kochzeit, int portionen, Menueart menueart,
                   boolean isVegan, boolean isVegetarisch,List<Food> foods,Unvertraeglichkeiten uv,String image){
        this.author = author;
        this.id = id;
        this.name = rezeptName;
        this.arbeitszeit = arbeitszeit;
        this.kochzeit = kochzeit;
        this.portionen = portionen;
        this.menueart = menueart;
        this.isVegan = isVegan;
        this.isVegetarisch = isVegetarisch;
        this.foods = foods;
        this.unvertraeglichkeiten = uv;
        this.image = image;
    }
}

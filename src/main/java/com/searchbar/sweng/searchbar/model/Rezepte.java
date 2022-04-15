package com.searchbar.sweng.searchbar.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;
import java.util.Map;


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
    @ElementCollection
    @MapKeyColumn(name ="food_name")
    @Column(name="rezepte_menge")
    @CollectionTable(name = "rezept_mengen_mapping")
    private Map<String,String> food;

    private int arbeitszeit;
    private int kochzeit;
    private int gesamtzeit;
    private int portionen;
    @Enumerated(EnumType.STRING)
    private Menueart menueart;
    private boolean isVegan;
    private boolean isVegetarisch;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unvertraeglichkeiten_id",referencedColumnName = "id")
    Unvertraeglichkeiten unvertraeglichkeiten;



    public Rezepte(int rezeptId,String rezeptName, int arbeitszeit, int kochzeit,int gesamtzeit, int portionen, Menueart menueart, boolean isVegan, boolean isVegetarisch,List<String> mengen,List<Food> foods){
        this.id = rezeptId;
        this.name = rezeptName;
        this.arbeitszeit = arbeitszeit;
        this.kochzeit = kochzeit;
        this.gesamtzeit = gesamtzeit;
        this.portionen = portionen;
        this.menueart = menueart;
        this.isVegan = isVegan;
        this.isVegetarisch = isVegetarisch;
    }
}

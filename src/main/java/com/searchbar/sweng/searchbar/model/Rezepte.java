package com.searchbar.sweng.searchbar.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Rezepte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rezeptId;
    private String rezeptName;
    private String speiseart;
    private Map<String,Double> mengen;
    private int arbeitszeit;
    private int kochzeit;
    private int gesamtzeit;
    private int portionen;
    private boolean isVegetarisch;
    private boolean isVegan;
    @Enumerated(EnumType.STRING)
    private Menueart menueart;
    private boolean fructose;
    private boolean lactose;
    private boolean histamine;
    @OneToOne(cascade = CascadeType.ALL,
            optional = false,
            mappedBy = "Rezepte")
    private Unvertraeglichkeiten unvertraeglichkeiten;

    public Rezepte(int rezeptId,String rezeptName, String speiseart, int arbeitszeit, int kochzeit, int portionen, boolean isVegetarisch,
                   boolean isVegan, boolean fructose, boolean lactose, boolean histamine, Menueart menueart){
        this.rezeptId = rezeptId;
        this.rezeptName = rezeptName;
        this.speiseart = speiseart;
        this.arbeitszeit = arbeitszeit;
        this.kochzeit = kochzeit;
        this.gesamtzeit = this.arbeitszeit + this.kochzeit;
        this.portionen = portionen;
        this.isVegetarisch = isVegetarisch;
        this.isVegan = isVegan;
        this.fructose = fructose;
        this.lactose = lactose;
        this.histamine = histamine;
        this.menueart = menueart;
    }
}

package com.searchbar.sweng.searchbar.inbound;

import com.searchbar.sweng.searchbar.model.Menueart;
import com.searchbar.sweng.searchbar.model.Rezepte;
import com.searchbar.sweng.searchbar.model.Unvertraeglichkeiten;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
@NoArgsConstructor
public class RezepteTO {
    private int id;
    private String name;
    private Map<String,String> food;
    private int arbeitszeit;
    private int kochzeit;
    private int gesamtzeit;
    private int portionen;
    private Menueart menueart;
    private boolean isVegan;
    private boolean isVegetarisch;
    Unvertraeglichkeiten unvertraeglichkeiten;

    public RezepteTO(Rezepte rezept){
        this.id = rezept.getId();
        this.name = rezept.getName();
        this.arbeitszeit = rezept.getArbeitszeit();
        this.kochzeit = rezept.getKochzeit();
        this.gesamtzeit = rezept.getGesamtzeit();
        this.portionen = rezept.getPortionen();
        this.menueart = rezept.getMenueart();
        this.isVegan = rezept.isVegan();
        this.isVegetarisch = rezept.isVegetarisch();
        this.food = rezept.getFood();
        this.unvertraeglichkeiten = rezept.getUnvertraeglichkeiten();
    }
}
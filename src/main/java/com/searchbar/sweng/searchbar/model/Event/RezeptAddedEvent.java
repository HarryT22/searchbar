package com.searchbar.sweng.searchbar.model.Event;

import com.searchbar.sweng.searchbar.model.Food;
import com.searchbar.sweng.searchbar.model.Menueart;
import com.searchbar.sweng.searchbar.model.Rezepte;
import com.searchbar.sweng.searchbar.model.Unvertraeglichkeiten;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RezeptAddedEvent {
    private int id;
    private String name;
    private List<Food> foods;
    private int arbeitszeit;
    private int kochzeit;
    private int portionen;
    private Menueart menueart;
    private boolean isVegan;
    private boolean isVegetarisch;
    private int gesamtzeit;
    private int kalorien;
    private int proteine;
    private Unvertraeglichkeiten unvertraeglichkeiten;
    @Lob
    private String image;

    public RezeptAddedEvent(Rezepte rezept){
        this.id = rezept.getId();
        this.name = rezept.getName();
        this.arbeitszeit = rezept.getArbeitszeit();
        this.kochzeit = rezept.getKochzeit();
        this.portionen = rezept.getPortionen();
        this.menueart = rezept.getMenueart();
        this.isVegan = rezept.isVegan();
        this.isVegetarisch = rezept.isVegetarisch();
        this.foods = rezept.getFoods();
        this.unvertraeglichkeiten = rezept.getUnvertraeglichkeiten();
        this.gesamtzeit = rezept.getGesamtzeit();
        this.kalorien = rezept.getCalories();
        this.proteine = rezept.getProteins();
        this.image = rezept.getImage();
    }
}


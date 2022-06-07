package com.searchbar.sweng.searchbar.inbound;

import com.searchbar.sweng.searchbar.model.Food;
import com.searchbar.sweng.searchbar.model.Rezepte;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class RezepteTO {
    /**
     * This class is a transfer object for the recipe class and adds some new attributes like gesamtzeit, kalorien and proteine.
     */
    private int id;
    private String name;
    private List<Food> foods;
    private int arbeitszeit;
    private int kochzeit;
    private int portionen;
    private String menueart;
    private boolean isVegan;
    private boolean isVegetarisch;
    private boolean fructose;
    private boolean lactose;
    private boolean histamine;
    private int gesamtzeit;
    private int kalorien;
    private int proteine;
    private String author;
    @Lob
    private String image;

    public RezepteTO(Rezepte rezept){
        this.name = rezept.getName();
        this.author = rezept.getAuthor();
        this.arbeitszeit = rezept.getArbeitszeit();
        this.kochzeit = rezept.getKochzeit();
        this.portionen = rezept.getPortionen();
        this.menueart = rezept.getMenueart().toString();
        this.isVegan = rezept.isVegan();
        this.isVegetarisch = rezept.isVegetarisch();
        this.foods = rezept.getFoods();
        this.fructose = rezept.getUnvertraeglichkeiten().isFructose();
        this.lactose = rezept.getUnvertraeglichkeiten().isLactose();
        this.histamine = rezept.getUnvertraeglichkeiten().isHistamine();
        this.gesamtzeit = rezept.getGesamtzeit();
        this.kalorien = rezept.getCalories();
        this.proteine = rezept.getProteins();
        this.image = rezept.getImage();
    }
}

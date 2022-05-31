package com.searchbar.sweng.searchbar.inbound;

import com.searchbar.sweng.searchbar.model.Food;
import com.searchbar.sweng.searchbar.model.Menueart;
import com.searchbar.sweng.searchbar.model.Rezepte;
import com.searchbar.sweng.searchbar.model.Unvertraeglichkeiten;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class RezepteTO {
    private int id;
    private String name;
    private List<Food> foods;
    private int arbeitszeit;
    private int kochzeit;
    private int portionen;
    private Menueart menueart;
    private boolean isVegan;
    private boolean isVegetarisch;
    private Unvertraeglichkeiten unvertraeglichkeiten;
    private int gesamtzeit;
    private int kalorien;
    private int proteine;
    private String author;
    @Lob
    private String image;

    public RezepteTO(Rezepte rezept){
        System.out.print("Hallo");
        this.name = rezept.getName();
        this.author = rezept.getAuthor();
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

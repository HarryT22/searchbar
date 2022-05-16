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
@ToString
@EqualsAndHashCode
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

    public int getGesamtZeit(){
        int gesamtZeit = getArbeitszeit()+getKochzeit();
        return gesamtZeit;
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

    public RezeptAddedEvent(int id,String rezeptName, int arbeitszeit, int kochzeit, int portionen, Menueart menueart,
                   boolean isVegan, boolean isVegetarisch,List<Food> foods,Unvertraeglichkeiten uv,String image){
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
        this.gesamtzeit = rezept.getGesamtZeit();
        this.kalorien = rezept.getCalories();
        this.proteine = rezept.getProteins();
        this.image = rezept.getImage();
    }
}


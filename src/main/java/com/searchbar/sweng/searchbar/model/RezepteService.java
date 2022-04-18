package com.searchbar.sweng.searchbar.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RezepteService {
    private RezepteRepository rezepteRepository;

    @Autowired
    public RezepteService(RezepteRepository rezepteRepository){
        this.rezepteRepository = rezepteRepository;
    }

    public List<Rezepte> listNormal(String name,boolean fructose,boolean lactose,boolean histamine,boolean isVegan,boolean isVegetarisch){
        List<Rezepte> rezepte = rezepteRepository.findAll(name);
        if(!rezepte.isEmpty()){
            for(Rezepte r: rezepte){
                if(isVegan && !r.isVegan()){
                    rezepte.remove(r);
                }
                if(isVegetarisch && !r.isVegetarisch()){
                    rezepte.remove(r);
                }
                if(fructose && !r.getUnvertraeglichkeiten().isFructose()){
                    rezepte.remove(r);
                }
                if(fructose && !r.getUnvertraeglichkeiten().isLactose()){
                    rezepte.remove(r);
                }
                if(fructose && !r.getUnvertraeglichkeiten().isHistamine()){
                    rezepte.remove(r);
                }
            }
            if(rezepte.size()>=5) {
                return rezepte.stream().limit(5).collect(Collectors.toList());
            }
            if(rezepte.isEmpty()){
                throw new NoSuchRecipieException("Ein Rezept mit diesen Filtern existiert nicht");
            }
            return rezepte;
        }else{
            throw new NoSuchRecipieException("Ein Rezept mit so einem Namen existiert nicht.");
        }
    }
}

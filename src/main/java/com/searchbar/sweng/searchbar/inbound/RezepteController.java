package com.searchbar.sweng.searchbar.inbound;


import com.searchbar.sweng.searchbar.model.Rezepte;
import com.searchbar.sweng.searchbar.model.RezepteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/searchbar")
public class RezepteController {

    private RezepteService rezepteService;

    @Autowired
    public RezepteController(RezepteService rezepteService){
        this.rezepteService=rezepteService;
    }

    @GetMapping("/free/{name}/{f}/{l}/{h}/{vegan}/{vegetarisch}")
    public List<RezepteTO> listNormal(@PathVariable("name") String name, @PathVariable("f") boolean fructose, @PathVariable("l") boolean lactose, @PathVariable("h") boolean histamine, @PathVariable("vegan") boolean isVegan, @PathVariable("vegetarisch") boolean isVegetarisch){
        List<RezepteTO> results = null;
        List<Rezepte> res = rezepteService.listNormal(name,fructose,lactose,histamine,isVegan,isVegetarisch);
        for(Rezepte r :res){
            results.add(new RezepteTO(r));
        }
        return results;
    }

}

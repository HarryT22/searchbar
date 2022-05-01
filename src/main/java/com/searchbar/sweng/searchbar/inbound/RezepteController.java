package com.searchbar.sweng.searchbar.inbound;


import com.searchbar.sweng.searchbar.model.Rezepte;
import com.searchbar.sweng.searchbar.model.RezepteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rest/searchbar")
public class RezepteController {

    private RezepteService rezepteService;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    public RezepteController(RezepteService rezepteService){
        this.rezepteService=rezepteService;
    }

    @GetMapping("/free/{name}/{f}/{l}/{h}/{vegan}/{vegetarisch}/{mink}/{maxk}/{minp}/{maxp}")
    public List<RezepteTO> listNormal(@PathVariable("name") String name, @PathVariable("f") boolean fructose, @PathVariable("l") boolean lactose,
                                      @PathVariable("h") boolean histamine, @PathVariable("vegan") boolean isVegan, @PathVariable("vegetarisch") boolean isVegetarisch,
                                      @PathVariable("mink")int minK,@PathVariable("maxk")int maxK,@PathVariable("minp")int minP,@PathVariable("maxp")int maxP){
        LOGGER.info("GET-Request of Rezepte received.");
        List<RezepteTO> results = null;
        List<Rezepte> res = rezepteService.listNormal(name,fructose,lactose,histamine,isVegan,isVegetarisch,minK,maxK,minP,maxP);
        for(Rezepte r :res){
            LOGGER.info("in for loop");
            results.add(new RezepteTO(r));
        }
        return results;
    }

}

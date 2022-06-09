package com.searchbar.sweng.searchbar.inbound;



import com.searchbar.sweng.searchbar.inbound.security.JwtValidator;
import com.searchbar.sweng.searchbar.model.*;

import com.searchbar.sweng.searchbar.model.Exceptions.NonValidFileException;
import com.searchbar.sweng.searchbar.model.Service.RezepteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping("/rest/searchbar")
public class RezepteController {
    private RezepteService rezepteService;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private JwtValidator jwtValidator;

    @Autowired
    public RezepteController(RezepteService rezepteService, JwtValidator jwtValidator) {
        this.rezepteService = rezepteService;
        this.jwtValidator = jwtValidator;
    }

    /**
     * Search for recipes with keyword, intolerances, vegan/vegetarian, amount of protein and calories
     * @param name of the recipe
     * @param fructose for intolerance filter
     * @param lactose for intolerance filter
     * @param histamine for intolerance filter
     * @param isVegan for preference filter
     * @param isVegetarisch for preference filter
     * @param minK minimum calories
     * @param maxK maximum calories
     * @param minP minimum proteins
     * @param maxP maximum proteins
     * @return list of recipes based on user role
     */
    @PreAuthorize("hasAnyAuthority('NORMAL','PREMIUM','ADMIN')")
    @GetMapping("/{name}/{f}/{l}/{h}/{vegan}/{vegetarisch}/{mink}/{maxk}/{minp}/{maxp}")
    public List<RezepteTO> listNormal(@RequestHeader String Authorization, @PathVariable("name") String name, @PathVariable("f") boolean fructose, @PathVariable("l") boolean lactose,
                                      @PathVariable("h") boolean histamine, @PathVariable("vegan") boolean isVegan, @PathVariable("vegetarisch") boolean isVegetarisch,
                                      @PathVariable("mink") int minK, @PathVariable("maxk") int maxK, @PathVariable("minp") int minP, @PathVariable("maxp") int maxP) {

        LOGGER.info("GET-Request of Rezepte received.");
        List<RezepteTO> results = new ArrayList<>();
        Authentication a = jwtValidator.getAuthentication(Authorization.substring(7));
        String role ="";

        if(a.getAuthorities().toString().contains("NORMAL")){
            role = "NORMAL";
            LOGGER.info("ROLE IS NORMAL");
        }
        if(a.getAuthorities().toString().contains("PREMIUM")){
            role = "PREMIUM";
            LOGGER.info("ROLE IS PREMIUM");
        }
        if(a.getAuthorities().toString().contains("ADMIN")){
            role = "ADMIN";
            LOGGER.info("ROLE IS ADMIN");
        }

        List<Rezepte> res = rezepteService.listNormal(role,name, fructose, lactose, histamine, isVegan, isVegetarisch, minK, maxK, minP, maxP);

        for (Rezepte r : res) {
           results.add(new RezepteTO(r));
        }

        return results;
    }


    /**
     * Save a recipe construct(without food list)
     * @param rezeptName name of the recipe
     * @param arbeitszeit time to prepare the recipe
     * @param kochzeit time to cook the recipe
     * @param portionen amount of portions
     * @param menueart (frühstück,mittagessen,abendessen)
     * @param isVegan  for preference filter
     * @param isVegetarisch for preference filter
     * @param h (histamine)intolerance
     * @param l (lactose)intolerance
     * @param f (fructose)intolerance
     * @param file (picture)
     * @return created recipe
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addR/{name}/{az}/{kz}/{p}/{ma}/{iv}/{ivt}/{h}/{l}/{f}")
    public RezepteTO saveRezept(@RequestHeader String Authorization,@PathVariable("name") String rezeptName,
                                @PathVariable("az") int arbeitszeit,@PathVariable("kz") int kochzeit,
                                @PathVariable("p") int portionen, @PathVariable("ma") Menueart menueart,
                                @PathVariable("iv") boolean isVegan, @PathVariable("ivt") boolean isVegetarisch,
                                @PathVariable("h") boolean h, @PathVariable("l") boolean l, @PathVariable("f") boolean f,
                                @RequestParam("file") MultipartFile file) {

        LOGGER.info("Received POST-Request /rest/searchbar/addR with parameter {},{},{},{},{},{},{},{},{},{}).",rezeptName,arbeitszeit,kochzeit,portionen,menueart,isVegan,isVegetarisch,h,l,f);
        String author = jwtValidator.getUserEmail(Authorization.substring(7));
        String image ="";

        if (file.getOriginalFilename() != null) {
            LOGGER.info(file.getOriginalFilename());
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if (fileName.contains("..")) {
                LOGGER.info("Not a valid filename.");
                throw new NonValidFileException("Not a valid file");
            }
            try {
                image = Base64.getEncoder().encodeToString(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Rezepte r =  rezepteService.saveRezept(author,rezeptName, arbeitszeit, kochzeit, portionen, menueart, isVegan, isVegetarisch, h, l, f,image);

        return new RezepteTO(r);
    }

    /**
     * Add food to food list of specific recipe(id)
     * @param id of the recipie that the food is getting added to
     * @param name of the food
     * @param kalorien of the food
     * @param protein of the food
     * @param menge of the food in the recipie
     * @return updated recipe object
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addF/{id}/{name}/{k}/{p}/{menge}")
    public RezepteTO addFood(@PathVariable("id")int id,@PathVariable("name") String name, @PathVariable("k") int kalorien, @PathVariable("p") int protein,
                             @PathVariable("menge") String menge) {
        LOGGER.info("Received POST-Request /rest/searchbar/addF/{} ).",id);
        Rezepte r = rezepteService.addFoodToRezept(name, kalorien, protein, menge, id);
        return new RezepteTO(r);
    }

    /**
     * Delete a recipe by id
     * @param id of the food that's going to be deleted
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteRezept(@PathVariable("id") int id) {
        LOGGER.info("Received DELETE-Request /rest/searchbar/delete/{} ).",id);
        rezepteService.deleteRezept(id);
    }

    /**
     * Delete a recipe by id
     * @param rId the ID of the recipie that is supposed to be changed.
     * @param fId the ID of the food that is supposed to be deleted from the list.
     * @return new RezepteTO with the new Foods-List
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deleteF/{rId}/{fId}")
    public RezepteTO deleteFoodFromRezept(@PathVariable("rId") int rId,@PathVariable("fId") int fId) {
        LOGGER.info("Received DELETE-Request /rest/searchbar/deleteF/{}/{} ).",rId,fId);
        Rezepte r =rezepteService.deleteFoodFromRezept(rId,fId);

        return new RezepteTO(r);
    }
}

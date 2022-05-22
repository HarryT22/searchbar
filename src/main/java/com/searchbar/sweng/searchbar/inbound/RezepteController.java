package com.searchbar.sweng.searchbar.inbound;


import com.searchbar.sweng.searchbar.inbound.security.JwtValidator;
import com.searchbar.sweng.searchbar.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



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

    @PreAuthorize("hasAnyAuthority('NORMAL','PREMIUM','ADMIN')")
    @GetMapping("/{name}/{f}/{l}/{h}/{vegan}/{vegetarisch}/{mink}/{maxk}/{minp}/{maxp}")
    public List<RezepteTO> listNormal(@RequestHeader String Authorization, @PathVariable("name") String name, @PathVariable("f") boolean fructose, @PathVariable("l") boolean lactose,
                                      @PathVariable("h") boolean histamine, @PathVariable("vegan") boolean isVegan, @PathVariable("vegetarisch") boolean isVegetarisch,
                                      @PathVariable("mink") int minK, @PathVariable("maxk") int maxK, @PathVariable("minp") int minP, @PathVariable("maxp") int maxP) {
        LOGGER.info("GET-Request of Rezepte received.");
        List<RezepteTO> results = new ArrayList<>();
        Collection<GrantedAuthority> roles = jwtValidator.getRoles(Authorization.substring(7));
        String role = "NORMAL";
        if(roles.contains(Role.PREMIUM)){
            role = "PREMIUM";
        }
        if(roles.contains(Role.ADMIN)){
            role = "ADMIN";
        }
        List<Rezepte> res = rezepteService.listNormal(role,name, fructose, lactose, histamine, isVegan, isVegetarisch, minK, maxK, minP, maxP);
        for (Rezepte r : res) {
            RezepteTO rto = new RezepteTO(r);
            results.add(rto);
        }
        return results;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addR")
    public void saveRezept(@RequestParam("file") MultipartFile file, @RequestParam("name") String rezeptName,
                           @RequestParam("az") int arbeitszeit, @RequestParam("kz") int kochzeit,
                           @RequestParam("p") int portionen, @RequestParam("ma") Menueart menueart,
                           @RequestParam("iv") boolean isVegan, @RequestParam("ivt") boolean isVegetarisch,
                           @RequestParam("h") boolean h, @RequestParam("l") boolean l, @RequestParam("f") boolean f) {
        rezepteService.saveRezept(file, rezeptName, arbeitszeit, kochzeit, portionen, menueart, isVegan, isVegetarisch, h, l, f);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addF")
    public void addFood(@RequestParam("name") String name, @RequestParam("k") int kalorien, @RequestParam("p") int protein,
                        @RequestParam("menge") String menge, @RequestParam("id") int id) {
        rezepteService.addFoodToRezept(name, kalorien, protein, menge, id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete")
    public void deleteRezept(@RequestParam("id") int id) {
        rezepteService.deleteRezept(id);
    }
}

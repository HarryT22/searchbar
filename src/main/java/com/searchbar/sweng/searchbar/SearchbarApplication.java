package com.searchbar.sweng.searchbar;

import com.searchbar.sweng.searchbar.model.Menueart;
import com.searchbar.sweng.searchbar.model.Rezepte;
import com.searchbar.sweng.searchbar.model.RezepteRepository;
import com.searchbar.sweng.searchbar.model.Unvertraeglichkeiten;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@SpringBootApplication
public class SearchbarApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchbarApplication.class, args);
    }
//    @Bean
//    CommandLineRunner commandLineRunner(RezepteRepository rp){
//        return args -> {
//            Unvertraeglichkeiten uv = new Unvertraeglichkeiten(1, true, true, true);
//            HashMap<String, String> food = new HashMap<>();
//            food.put("Mehl", "200Gramm");
//            Rezepte test = new Rezepte(1, "Fleisch Y", 1, 2, 3, 4, Menueart.MITTAGESSEN, true, true, food, uv);
//            rp.save(test);
//            List<Rezepte> result =rp.findAll("Fleisch");
//            for (Rezepte r : result){
//                System.out.println(r.getName());
//            }
//        };
// }

}

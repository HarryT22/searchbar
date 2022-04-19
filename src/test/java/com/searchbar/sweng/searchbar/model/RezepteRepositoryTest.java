package com.searchbar.sweng.searchbar.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.searchbar.sweng.searchbar.model.Menueart.ABENDESSEN;

@DataJpaTest
class RezepteRepositoryTest {
    @Autowired
    private RezepteRepository underTest;

    @Test
    void itShouldFindAll() {
        Unvertraeglichkeiten uv = new Unvertraeglichkeiten(1,false,false,false);
        Map<String,String> test = new HashMap<>();
        Rezepte r = new Rezepte("Fleisch A",2,3,5,2,ABENDESSEN,false,false,test,uv);
        System.out.println(r.getId());
        underTest.save(r);
        List<Rezepte> res = underTest.findAll("Fleisch");
        assertEquals(1,res.get(0).getId());
    }
}
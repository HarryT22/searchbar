package com.searchbar.sweng.searchbar;

import com.searchbar.sweng.searchbar.inbound.RezepteController;
import com.searchbar.sweng.searchbar.inbound.RezepteTO;
import com.searchbar.sweng.searchbar.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = RezepteController.class)
public class RezepteControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RezepteService rezepteService;

    private Unvertraeglichkeiten uv;
    private Rezepte r;
    private List<Food> foods;
    private Food f;
    private RezepteTO rto;

    @BeforeEach
    public void setUp() throws Exception {
        this.uv = new Unvertraeglichkeiten(false, false, false);
        this.f = new Food("Fleisch", 200, 200, "400Gramm");
        this.foods = new ArrayList<>();
        this.foods.add(f);
        this.r = new Rezepte( "Fleisch A", 4, 2, 6, 2, Menueart.FRÜHSTÜCK, false, false, foods, uv);
        this.rto = new RezepteTO(r);
    }

    @Test
    public void listNormalTest() throws Exception {
        List<RezepteTO> rl = new ArrayList<>();
        List<Rezepte> test = new ArrayList<>();
        rl.add(rto);
        test.add(r);
        given(this.rezepteService.listNormal("Fleisch", false, false, false,
                false, false, 0, 1000, 0, 1000)).willReturn(test);
        this.mvc.perform(get("/rest/searchbar/free/{name}/{f}/{l}/{h}/{vegan}/{vegetarisch}/{mink}/{maxk}/{minp}/{maxp}",
                        "Fleisch", false, false, false, false, false, 0, 1000, 0, 1000))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Fleisch A"))
                .andExpect(content().json("{'id': 1,'name':'Fleisch A','arbeitszeit': 4,'kochzeit': 2,'gesamtzeit': 6,'portionen': 2,'menueart': 'FRÜHSTÜCK','vegan': 0,'vegetarisch': 0,'foods': [{'id': null,'name': 'Fleisch','proteine':200,'kalorien':200,'menge':'400Gramm'}],'unvertraeglichkeiten': [{'id': null, 'histamine': 0,'fructose': 0,'lactose': 0}]}"));
    }
}

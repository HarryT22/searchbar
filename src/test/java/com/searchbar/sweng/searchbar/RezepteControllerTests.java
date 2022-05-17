package com.searchbar.sweng.searchbar;

import com.searchbar.sweng.searchbar.inbound.RezepteController;
import com.searchbar.sweng.searchbar.inbound.security.JwtValidator;
import com.searchbar.sweng.searchbar.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = RezepteController.class)
public class RezepteControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RezepteService rezepteService;

    @MockBean
    private JwtValidator jwtValidator;

    private Unvertraeglichkeiten uv;
    private Unvertraeglichkeiten uv2;
    private Unvertraeglichkeiten uv3;
    private Unvertraeglichkeiten uv4;
    private Unvertraeglichkeiten uv5;
    private Unvertraeglichkeiten uv6;

    private Rezepte r;
    private Rezepte r2;
    private Rezepte r3;
    private Rezepte r4;
    private Rezepte r5;
    private Rezepte r6;

    private List<Food> foods;
    private List<Food> foods2;
    private List<Food> foods3;
    private List<Food> foods4;
    private List<Food> foods5;
    private List<Food> foods6;

    private Food f;
    private Food f2;
    private Food f3;
    private Food f4;
    private Food f5;
    private Food f6;

    private final String AUTH_HEADER = "Bearer ANY-JWT-STRING";;
    private final String TEST_USER_EMAIL = "harry@hacker.de";

    @BeforeEach
    public void setUp() throws Exception {
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(TEST_USER_EMAIL)
                .password("***")
                .authorities(Role.ADMIN.getAuthority())
                .build();

        given(jwtValidator.isValidJWT(any(String.class))).willReturn(true);
        given(jwtValidator.getUserEmail(any(String.class))).willReturn(TEST_USER_EMAIL);
        given(jwtValidator.resolveToken(any(HttpServletRequest.class))).willReturn(AUTH_HEADER.substring(7));
        given(jwtValidator.getAuthentication(any(String.class))).willReturn(new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()));

        this.uv = new Unvertraeglichkeiten(false, false, false);
        this.uv2 = new Unvertraeglichkeiten(true, true, true);
        this.uv3 = new Unvertraeglichkeiten(false, false, false);
        this.uv4 = new Unvertraeglichkeiten(false, false, false);
        this.uv5 = new Unvertraeglichkeiten(false, false, false);
        this.uv6 = new Unvertraeglichkeiten(false, false, false);

        this.f = new Food("Fleisch", 200, 200, "400Gramm");
        this.f2 = new Food("Fleisch",900,900,"4 Kilo");
        this.f3= new Food("Fleisch", 200, 200, "400Gramm");
        this.f4 = new Food("Fleisch",900,900,"4 Kilo");
        this.f5 = new Food("Fleisch", 200, 200, "400Gramm");
        this.f6 = new Food("Fleisch",900,900,"4 Kilo");

        this.foods = new ArrayList<>();
        this.foods2 = new ArrayList<>();
        this.foods3 = new ArrayList<>();
        this.foods4 = new ArrayList<>();
        this.foods5 = new ArrayList<>();
        this.foods6 = new ArrayList<>();

        this.foods.add(f);
        this.foods2.add(f2);
        this.foods3.add(f3);
        this.foods4.add(f4);
        this.foods5.add(f5);
        this.foods6.add(f6);

        this.r = new Rezepte("Fleisch A",4,2,2,Menueart.FRÜHSTÜCK,false,false,foods,uv);
        this.r2 = new Rezepte( "Fleisch B", 4, 2, 2, Menueart.FRÜHSTÜCK, false, false, foods2, uv2);
        this.r3 = new Rezepte( "Fleisch C", 4, 2, 2, Menueart.FRÜHSTÜCK, false, false, foods3, uv3);
        this.r4 = new Rezepte( "Fleisch D", 4, 2, 2, Menueart.FRÜHSTÜCK, false, false, foods4, uv4);
        this.r5 = new Rezepte( "Fleisch E", 4, 2, 2, Menueart.FRÜHSTÜCK, false, false, foods5, uv5);
        this.r6 = new Rezepte( "Fleisch F", 4, 2, 2, Menueart.FRÜHSTÜCK, false, false, foods6, uv6);
    }

    @Test
    public void listEins() throws Exception {
        List<Rezepte> test = new ArrayList<>();
        test.add(r);
        given(this.rezepteService.listNormal("Fleisch", false, false, false,
                false, false, 0, 1000, 0, 1000)).willReturn(test);
        this.mvc.perform(get("/rest/searchbar/{name}/{f}/{l}/{h}/{vegan}/{vegetarisch}/{mink}/{maxk}/{minp}/{maxp}",
                        "Fleisch", false, false, false, false, false, 0, 1000, 0, 1000)
                        .header("Authorization",this.AUTH_HEADER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':0,'name':'Fleisch A','foods':[{'id':0,'name':'Fleisch','proteine':200,'kalorien':200,'menge':'400Gramm'}],'arbeitszeit':4,'kochzeit':2,'portionen':2,'menueart':'FRÜHSTÜCK','unvertraeglichkeiten':{'id':0,'histamine':false,'fructose':false,'lactose':false},'kalorien':200,'proteine':200,'vegetarisch':false,'vegan':false}]"));
        verify(rezepteService, Mockito.times(1)).listNormal("Fleisch", false, false, false, false, false, 0, 1000, 0, 1000);
    }
   /*
    @Test
    public void listMehrFünf() throws Exception {
        List<Rezepte> test = new ArrayList<>();
        test.add(r);
        test.add(r2);
        test.add(r3);
        test.add(r4);
        test.add(r5);
        given(this.rezepteService.listNormal("Fleisch", false, false, false,
                false, false, 0, 1000, 0, 1000)).willReturn(test);
        this.mvc.perform(get("/rest/searchbar/free/{name}/{f}/{l}/{h}/{vegan}/{vegetarisch}/{mink}/{maxk}/{minp}/{maxp}",
                        "Fleisch", false, false, false, false, false, 0, 1000, 0, 1000))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(content().json("[{\"id\":1,\"name\":\"Fleisch A\",\"foods\":[{\"id\":0,\"name\":\"Fleisch\",\"proteine\":200,\"kalorien\":200,\"menge\":\"400Gramm\"}],\"arbeitszeit\":4,\"kochzeit\":2,\"gesamtzeit\":6,\"portionen\":2,\"menueart\":\"FRÜHSTÜCK\",\"unvertraeglichkeiten\":{\"id\":0,\"histamine\":false,\"fructose\":false,\"lactose\":false},\"kalorien\":200,\"proteine\":200,\"vegan\":false,\"vegetarisch\":false},{\"id\":2,\"name\":\"Fleisch B\",\"foods\":[{\"id\":0,\"name\":\"Fleisch\",\"proteine\":900,\"kalorien\":900,\"menge\":\"4 Kilo\"}],\"arbeitszeit\":4,\"kochzeit\":2,\"gesamtzeit\":6,\"portionen\":2,\"menueart\":\"FRÜHSTÜCK\",\"unvertraeglichkeiten\":{\"id\":0,\"histamine\":true,\"fructose\":true,\"lactose\":true},\"kalorien\":900,\"proteine\":900,\"vegan\":false,\"vegetarisch\":false},{\"id\":1,\"name\":\"Fleisch C\",\"foods\":[{\"id\":0,\"name\":\"Fleisch\",\"proteine\":200,\"kalorien\":200,\"menge\":\"400Gramm\"}],\"arbeitszeit\":4,\"kochzeit\":2,\"gesamtzeit\":6,\"portionen\":2,\"menueart\":\"FRÜHSTÜCK\",\"unvertraeglichkeiten\":{\"id\":0,\"histamine\":false,\"fructose\":false,\"lactose\":false},\"kalorien\":200,\"proteine\":200,\"vegan\":false,\"vegetarisch\":false},{\"id\":2,\"name\":\"Fleisch D\",\"foods\":[{\"id\":0,\"name\":\"Fleisch\",\"proteine\":900,\"kalorien\":900,\"menge\":\"4 Kilo\"}],\"arbeitszeit\":4,\"kochzeit\":2,\"gesamtzeit\":6,\"portionen\":2,\"menueart\":\"FRÜHSTÜCK\",\"unvertraeglichkeiten\":{\"id\":0,\"histamine\":false,\"fructose\":false,\"lactose\":false},\"kalorien\":900,\"proteine\":900,\"vegan\":false,\"vegetarisch\":false},{\"id\":1,\"name\":\"Fleisch E\",\"foods\":[{\"id\":0,\"name\":\"Fleisch\",\"proteine\":200,\"kalorien\":200,\"menge\":\"400Gramm\"}],\"arbeitszeit\":4,\"kochzeit\":2,\"gesamtzeit\":6,\"portionen\":2,\"menueart\":\"FRÜHSTÜCK\",\"unvertraeglichkeiten\":{\"id\":0,\"histamine\":false,\"fructose\":false,\"lactose\":false},\"kalorien\":200,\"proteine\":200,\"vegan\":false,\"vegetarisch\":false}]"));
    }*/
}

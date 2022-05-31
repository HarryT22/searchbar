package com.searchbar.sweng.searchbar;

import com.searchbar.sweng.searchbar.inbound.RezepteController;
import com.searchbar.sweng.searchbar.inbound.security.JwtValidator;
import com.searchbar.sweng.searchbar.model.*;
import com.searchbar.sweng.searchbar.model.Service.RezepteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = RezepteController.class)
public class RezepteControllerTests {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

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
    private Rezepte r10;
    private Rezepte r15;
    private Rezepte r7;
    private Rezepte r8;
    private Rezepte r9;
    private Rezepte r11;

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

    private final String AUTH_HEADER = "Bearer ANY-JWT-STRING";
    ;
    private final String TEST_USER_EMAIL = "harry@hacker.de";

    @BeforeEach
    public void setUp() throws Exception {

        this.uv = new Unvertraeglichkeiten(false, false, false);
        this.uv2 = new Unvertraeglichkeiten(true, true, true);
        this.uv3 = new Unvertraeglichkeiten(false, false, false);
        this.uv4 = new Unvertraeglichkeiten(false, false, false);
        this.uv5 = new Unvertraeglichkeiten(false, false, false);
        this.uv6 = new Unvertraeglichkeiten(false, false, false);

        this.f = new Food("Fleisch", 200, 200, "400Gramm");
        this.f2 = new Food("Fleisch", 900, 900, "4 Kilo");
        this.f3 = new Food("Fleisch", 200, 200, "400Gramm");
        this.f4 = new Food("Fleisch", 900, 900, "4 Kilo");
        this.f5 = new Food("Fleisch", 200, 200, "400Gramm");
        this.f6 = new Food("Fleisch", 900, 900, "4 Kilo");

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


        this.r = new Rezepte("sandra", "Fleisch A", 4, 2, 2, Menueart.FRÜHSTÜCK, false, false, foods, uv);
        this.r2 = new Rezepte("sandra", "Fleisch B", 4, 2, 2, Menueart.FRÜHSTÜCK, false, false, foods2, uv2);
        this.r3 = new Rezepte("sandra", "Fleisch C", 4, 2, 2, Menueart.FRÜHSTÜCK, false, false, foods3, uv3);
        this.r4 = new Rezepte("sandra", "Fleisch D", 4, 2, 2, Menueart.FRÜHSTÜCK, false, false, foods4, uv4);
        this.r5 = new Rezepte("sandra", "Fleisch E", 4, 2, 2, Menueart.FRÜHSTÜCK, false, false, foods5, uv5);
        this.r6 = new Rezepte("sandra", "Fleisch F", 4, 2, 2, Menueart.FRÜHSTÜCK, false, false, foods6, uv6);
        this.r7 = new Rezepte("sandra", "Fleisch G", 4, 2, 2, Menueart.FRÜHSTÜCK, false, false, foods, uv);
        this.r8 = new Rezepte("sandra", "Fleisch H", 4, 2, 2, Menueart.FRÜHSTÜCK, false, false, foods2, uv2);
        this.r9 = new Rezepte("sandra", "Fleisch I", 4, 2, 2, Menueart.FRÜHSTÜCK, false, false, foods3, uv3);
        this.r11 = new Rezepte("sandra", "Fleisch J", 4, 2, 2, Menueart.FRÜHSTÜCK, false, false, foods4, uv4);
        this.r10 = new Rezepte("sandra", "Brot mit Käse", 2, 3, 2, Menueart.FRÜHSTÜCK, false, false, foods, uv);


    }

    @Test
    public void listEins() throws Exception {
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(TEST_USER_EMAIL)
                .password("***")
                .authorities(Role.NORMAL.getAuthority())
                .build();
        given(jwtValidator.isValidJWT(any(String.class))).willReturn(true);
        given(jwtValidator.getUserEmail(any(String.class))).willReturn(TEST_USER_EMAIL);
        given(jwtValidator.resolveToken(any(HttpServletRequest.class))).willReturn(AUTH_HEADER.substring(7));
        given(jwtValidator.getAuthentication(any(String.class))).willReturn(new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()));

        List<Rezepte> test = new ArrayList<>();
        test.add(r);
        given(this.rezepteService.listNormal("NORMAL", "Fleisch", false, false, false,
                false, false, 0, 1000, 0, 1000)).willReturn(test);
        this.mvc.perform(get("/rest/searchbar/{name}/{f}/{l}/{h}/{vegan}/{vegetarisch}/{mink}/{maxk}/{minp}/{maxp}",
                        "Fleisch", false, false, false, false, false, 0, 1000, 0, 1000)
                        .header("Authorization", this.AUTH_HEADER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':0,'name':'Fleisch A','foods':[{'id':0,'name':'Fleisch','proteine':200,'kalorien':200,'menge':'400Gramm'}],'arbeitszeit':4,'kochzeit':2,'portionen':2,'menueart':'FRÜHSTÜCK','unvertraeglichkeiten':{'id':0,'histamine':false,'fructose':false,'lactose':false},'kalorien':200,'proteine':200,'vegetarisch':false,'vegan':false}]"));
        verify(rezepteService, Mockito.times(1)).listNormal("NORMAL", "Fleisch", false, false, false, false, false, 0, 1000, 0, 1000);
    }

    @Test
    public void listMehrFuenf() throws Exception {
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(TEST_USER_EMAIL)
                .password("***")
                .authorities(Role.NORMAL.getAuthority())
                .build();
        given(jwtValidator.isValidJWT(any(String.class))).willReturn(true);
        given(jwtValidator.getUserEmail(any(String.class))).willReturn(TEST_USER_EMAIL);
        given(jwtValidator.resolveToken(any(HttpServletRequest.class))).willReturn(AUTH_HEADER.substring(7));
        given(jwtValidator.getAuthentication(any(String.class))).willReturn(new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()));

        List<Rezepte> test = new ArrayList<>();
        test.add(r);
        test.add(r2);
        test.add(r3);
        test.add(r4);
        test.add(r5);
        given(this.rezepteService.listNormal("NORMAL", "Fleisch", false, false, false,
                false, false, 0, 1000, 0, 1000)).willReturn(test);
        this.mvc.perform(get("/rest/searchbar/{name}/{f}/{l}/{h}/{vegan}/{vegetarisch}/{mink}/{maxk}/{minp}/{maxp}",
                        "Fleisch", false, false, false, false, false, 0, 1000, 0, 1000)
                        .header("Authorization", this.AUTH_HEADER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":0,\"name\":\"Fleisch A\",\"foods\":[{\"id\":0,\"name\":\"Fleisch\",\"proteine\":200,\"kalorien\":200,\"menge\":\"400Gramm\",\"version\":0}],\"arbeitszeit\":4,\"kochzeit\":2,\"portionen\":2,\"menueart\":\"FRÜHSTÜCK\",\"unvertraeglichkeiten\":{\"id\":0,\"histamine\":false,\"fructose\":false,\"lactose\":false,\"version\":0},\"gesamtzeit\":6,\"kalorien\":200,\"proteine\":200,\"author\":\"sandra\",\"image\":null,\"vegetarisch\":false,\"vegan\":false},{\"id\":0,\"name\":\"Fleisch B\",\"foods\":[{\"id\":0,\"name\":\"Fleisch\",\"proteine\":900,\"kalorien\":900,\"menge\":\"4 Kilo\",\"version\":0}],\"arbeitszeit\":4,\"kochzeit\":2,\"portionen\":2,\"menueart\":\"FRÜHSTÜCK\",\"unvertraeglichkeiten\":{\"id\":0,\"histamine\":true,\"fructose\":true,\"lactose\":true,\"version\":0},\"gesamtzeit\":6,\"kalorien\":900,\"proteine\":900,\"author\":\"sandra\",\"image\":null,\"vegetarisch\":false,\"vegan\":false},{\"id\":0,\"name\":\"Fleisch C\",\"foods\":[{\"id\":0,\"name\":\"Fleisch\",\"proteine\":200,\"kalorien\":200,\"menge\":\"400Gramm\",\"version\":0}],\"arbeitszeit\":4,\"kochzeit\":2,\"portionen\":2,\"menueart\":\"FRÜHSTÜCK\",\"unvertraeglichkeiten\":{\"id\":0,\"histamine\":false,\"fructose\":false,\"lactose\":false,\"version\":0},\"gesamtzeit\":6,\"kalorien\":200,\"proteine\":200,\"author\":\"sandra\",\"image\":null,\"vegetarisch\":false,\"vegan\":false},{\"id\":0,\"name\":\"Fleisch D\",\"foods\":[{\"id\":0,\"name\":\"Fleisch\",\"proteine\":900,\"kalorien\":900,\"menge\":\"4 Kilo\",\"version\":0}],\"arbeitszeit\":4,\"kochzeit\":2,\"portionen\":2,\"menueart\":\"FRÜHSTÜCK\",\"unvertraeglichkeiten\":{\"id\":0,\"histamine\":false,\"fructose\":false,\"lactose\":false,\"version\":0},\"gesamtzeit\":6,\"kalorien\":900,\"proteine\":900,\"author\":\"sandra\",\"image\":null,\"vegetarisch\":false,\"vegan\":false},{\"id\":0,\"name\":\"Fleisch E\",\"foods\":[{\"id\":0,\"name\":\"Fleisch\",\"proteine\":200,\"kalorien\":200,\"menge\":\"400Gramm\",\"version\":0}],\"arbeitszeit\":4,\"kochzeit\":2,\"portionen\":2,\"menueart\":\"FRÜHSTÜCK\",\"unvertraeglichkeiten\":{\"id\":0,\"histamine\":false,\"fructose\":false,\"lactose\":false,\"version\":0},\"gesamtzeit\":6,\"kalorien\":200,\"proteine\":200,\"author\":\"sandra\",\"image\":null,\"vegetarisch\":false,\"vegan\":false}]"));

        verify(rezepteService, Mockito.times(1)).listNormal("NORMAL", "Fleisch", false, false, false, false, false, 0, 1000, 0, 1000);
    }

    @Test
    public void listZehn() throws Exception {
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(TEST_USER_EMAIL)
                .password("***")
                .authorities(Role.ADMIN.getAuthority())
                .build();
        given(jwtValidator.isValidJWT(any(String.class))).willReturn(true);
        given(jwtValidator.getUserEmail(any(String.class))).willReturn(TEST_USER_EMAIL);
        given(jwtValidator.resolveToken(any(HttpServletRequest.class))).willReturn(AUTH_HEADER.substring(7));
        given(jwtValidator.getAuthentication(any(String.class))).willReturn(new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()));

        List<Rezepte> test = new ArrayList<>();
        test.add(r);
        test.add(r2);
        test.add(r3);
        test.add(r4);
        test.add(r5);
        test.add(r6);
        test.add(r7);
        test.add(r8);
        test.add(r9);
        test.add(r11);
        given(this.rezepteService.listNormal("ADMIN", "Fleisch", false, false, false,
                false, false, 0, 1000, 0, 1000)).willReturn(test);
        this.mvc.perform(get("/rest/searchbar/{name}/{f}/{l}/{h}/{vegan}/{vegetarisch}/{mink}/{maxk}/{minp}/{maxp}",
                        "Fleisch", false, false, false, false, false, 0, 1000, 0, 1000)
                        .header("Authorization", this.AUTH_HEADER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":0,\"name\":\"Fleisch A\",\"foods\":[{\"id\":0,\"name\":\"Fleisch\",\"proteine\":200,\"kalorien\":200,\"menge\":\"400Gramm\",\"version\":0}],\"arbeitszeit\":4,\"kochzeit\":2,\"portionen\":2,\"menueart\":\"FRÜHSTÜCK\",\"unvertraeglichkeiten\":{\"id\":0,\"histamine\":false,\"fructose\":false,\"lactose\":false,\"version\":0},\"gesamtzeit\":6,\"kalorien\":200,\"proteine\":200,\"author\":\"sandra\",\"image\":null,\"vegan\":false,\"vegetarisch\":false},{\"id\":0,\"name\":\"Fleisch B\",\"foods\":[{\"id\":0,\"name\":\"Fleisch\",\"proteine\":900,\"kalorien\":900,\"menge\":\"4 Kilo\",\"version\":0}],\"arbeitszeit\":4,\"kochzeit\":2,\"portionen\":2,\"menueart\":\"FRÜHSTÜCK\",\"unvertraeglichkeiten\":{\"id\":0,\"histamine\":true,\"fructose\":true,\"lactose\":true,\"version\":0},\"gesamtzeit\":6,\"kalorien\":900,\"proteine\":900,\"author\":\"sandra\",\"image\":null,\"vegan\":false,\"vegetarisch\":false},{\"id\":0,\"name\":\"Fleisch C\",\"foods\":[{\"id\":0,\"name\":\"Fleisch\",\"proteine\":200,\"kalorien\":200,\"menge\":\"400Gramm\",\"version\":0}],\"arbeitszeit\":4,\"kochzeit\":2,\"portionen\":2,\"menueart\":\"FRÜHSTÜCK\",\"unvertraeglichkeiten\":{\"id\":0,\"histamine\":false,\"fructose\":false,\"lactose\":false,\"version\":0},\"gesamtzeit\":6,\"kalorien\":200,\"proteine\":200,\"author\":\"sandra\",\"image\":null,\"vegan\":false,\"vegetarisch\":false},{\"id\":0,\"name\":\"Fleisch D\",\"foods\":[{\"id\":0,\"name\":\"Fleisch\",\"proteine\":900,\"kalorien\":900,\"menge\":\"4 Kilo\",\"version\":0}],\"arbeitszeit\":4,\"kochzeit\":2,\"portionen\":2,\"menueart\":\"FRÜHSTÜCK\",\"unvertraeglichkeiten\":{\"id\":0,\"histamine\":false,\"fructose\":false,\"lactose\":false,\"version\":0},\"gesamtzeit\":6,\"kalorien\":900,\"proteine\":900,\"author\":\"sandra\",\"image\":null,\"vegan\":false,\"vegetarisch\":false},{\"id\":0,\"name\":\"Fleisch E\",\"foods\":[{\"id\":0,\"name\":\"Fleisch\",\"proteine\":200,\"kalorien\":200,\"menge\":\"400Gramm\",\"version\":0}],\"arbeitszeit\":4,\"kochzeit\":2,\"portionen\":2,\"menueart\":\"FRÜHSTÜCK\",\"unvertraeglichkeiten\":{\"id\":0,\"histamine\":false,\"fructose\":false,\"lactose\":false,\"version\":0},\"gesamtzeit\":6,\"kalorien\":200,\"proteine\":200,\"author\":\"sandra\",\"image\":null,\"vegan\":false,\"vegetarisch\":false},{\"id\":0,\"name\":\"Fleisch F\",\"foods\":[{\"id\":0,\"name\":\"Fleisch\",\"proteine\":900,\"kalorien\":900,\"menge\":\"4 Kilo\",\"version\":0}],\"arbeitszeit\":4,\"kochzeit\":2,\"portionen\":2,\"menueart\":\"FRÜHSTÜCK\",\"unvertraeglichkeiten\":{\"id\":0,\"histamine\":false,\"fructose\":false,\"lactose\":false,\"version\":0},\"gesamtzeit\":6,\"kalorien\":900,\"proteine\":900,\"author\":\"sandra\",\"image\":null,\"vegan\":false,\"vegetarisch\":false},{\"id\":0,\"name\":\"Fleisch G\",\"foods\":[{\"id\":0,\"name\":\"Fleisch\",\"proteine\":200,\"kalorien\":200,\"menge\":\"400Gramm\",\"version\":0}],\"arbeitszeit\":4,\"kochzeit\":2,\"portionen\":2,\"menueart\":\"FRÜHSTÜCK\",\"unvertraeglichkeiten\":{\"id\":0,\"histamine\":false,\"fructose\":false,\"lactose\":false,\"version\":0},\"gesamtzeit\":6,\"kalorien\":200,\"proteine\":200,\"author\":\"sandra\",\"image\":null,\"vegan\":false,\"vegetarisch\":false},{\"id\":0,\"name\":\"Fleisch H\",\"foods\":[{\"id\":0,\"name\":\"Fleisch\",\"proteine\":900,\"kalorien\":900,\"menge\":\"4 Kilo\",\"version\":0}],\"arbeitszeit\":4,\"kochzeit\":2,\"portionen\":2,\"menueart\":\"FRÜHSTÜCK\",\"unvertraeglichkeiten\":{\"id\":0,\"histamine\":true,\"fructose\":true,\"lactose\":true,\"version\":0},\"gesamtzeit\":6,\"kalorien\":900,\"proteine\":900,\"author\":\"sandra\",\"image\":null,\"vegan\":false,\"vegetarisch\":false},{\"id\":0,\"name\":\"Fleisch I\",\"foods\":[{\"id\":0,\"name\":\"Fleisch\",\"proteine\":200,\"kalorien\":200,\"menge\":\"400Gramm\",\"version\":0}],\"arbeitszeit\":4,\"kochzeit\":2,\"portionen\":2,\"menueart\":\"FRÜHSTÜCK\",\"unvertraeglichkeiten\":{\"id\":0,\"histamine\":false,\"fructose\":false,\"lactose\":false,\"version\":0},\"gesamtzeit\":6,\"kalorien\":200,\"proteine\":200,\"author\":\"sandra\",\"image\":null,\"vegan\":false,\"vegetarisch\":false},{\"id\":0,\"name\":\"Fleisch J\",\"foods\":[{\"id\":0,\"name\":\"Fleisch\",\"proteine\":900,\"kalorien\":900,\"menge\":\"4 Kilo\",\"version\":0}],\"arbeitszeit\":4,\"kochzeit\":2,\"portionen\":2,\"menueart\":\"FRÜHSTÜCK\",\"unvertraeglichkeiten\":{\"id\":0,\"histamine\":false,\"fructose\":false,\"lactose\":false,\"version\":0},\"gesamtzeit\":6,\"kalorien\":900,\"proteine\":900,\"author\":\"sandra\",\"image\":null,\"vegan\":false,\"vegetarisch\":false}]"));
        verify(rezepteService, Mockito.times(1)).listNormal("ADMIN", "Fleisch", false, false, false, false, false, 0, 1000, 0, 1000);
    }



    @Test
    public void saveRezeptWorks() throws Exception {
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(TEST_USER_EMAIL)
                .password("***")
                .authorities(Role.ADMIN.getAuthority())
                .build();
        given(jwtValidator.isValidJWT(any(String.class))).willReturn(true);
        given(jwtValidator.getUserEmail(any(String.class))).willReturn(TEST_USER_EMAIL);
        given(jwtValidator.resolveToken(any(HttpServletRequest.class))).willReturn(AUTH_HEADER.substring(7));
        given(jwtValidator.getAuthentication(any(String.class))).willReturn(new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()));

        ArrayList<Food> foodsave = new ArrayList<>();
        MockMultipartFile mockMF = new MockMultipartFile("images","image1","image/png","test".getBytes());
        Rezepte save = new Rezepte("Sandra","Kase",2,4,2,Menueart.MITTAGESSEN,false,
                false,foodsave,uv,"");

        given(this.rezepteService.saveRezept("Sandra","Käse",2,4,2,
                Menueart.FRÜHSTÜCK,false,false,false,false,false,"")).willReturn(save);

        this.mvc.perform(multipart("/rest/searchbar/addR/{name}/{az}/{kz}/{p}/{ma}/{iv}/{ivt}/{h}/{l}/{f}",
                "Kase",2,4,2,Menueart.MITTAGESSEN,false,false,false,false,false)
                .file("file",mockMF.getBytes())
                .header("Authorization",this.AUTH_HEADER))
                .andDo(print());



    }

    @Test
    public void addFoodToRezeptWorks() throws Exception {
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(TEST_USER_EMAIL)
                .password("***")
                .authorities(Role.ADMIN.getAuthority())
                .build();
        given(jwtValidator.isValidJWT(any(String.class))).willReturn(true);
        given(jwtValidator.getUserEmail(any(String.class))).willReturn(TEST_USER_EMAIL);
        given(jwtValidator.resolveToken(any(HttpServletRequest.class))).willReturn(AUTH_HEADER.substring(7));
        given(jwtValidator.getAuthentication(any(String.class))).willReturn(new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()));


        List<Food> foods = new ArrayList<>();
        Unvertraeglichkeiten uv = new Unvertraeglichkeiten(false, false, false);
        foods.add(new Food("Käse", 200, 400, "400G"));
        r15 = new Rezepte("Sandra", 200, "Fleisch I", 2, 4, 2, Menueart.FRÜHSTÜCK, false, false, foods, uv);
        given(this.rezepteService.addFoodToRezept("Käse", 200, 400, "400G", 15)).willReturn(r15);
        this.mvc.perform(post("/rest/searchbar/{id}/addF/{name}/{k}/{p}/{menge}", 15, "Käse", 200, 400, "400G")
                        .header("Authorization", this.AUTH_HEADER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":200,\"name\":\"Fleisch I\",\"foods\":[{\"id\":0,\"name\":\"Käse\",\"proteine\":200,\"kalorien\":400,\"menge\":\"400G\",\"version\":0}],\"arbeitszeit\":2,\"kochzeit\":4,\"portionen\":2,\"menueart\":\"FRÜHSTÜCK\",\"unvertraeglichkeiten\":{\"id\":0,\"histamine\":false,\"fructose\":false,\"lactose\":false,\"version\":0},\"gesamtzeit\":6,\"kalorien\":400,\"proteine\":200,\"author\":\"Sandra\",\"image\":null,\"vegan\":false,\"vegetarisch\":false}"));
        verify(rezepteService, Mockito.times(1)).addFoodToRezept("Käse", 200, 400, "400G", 15);
    }

    @Test
    public void deleteRezeptWorks() throws Exception {
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(TEST_USER_EMAIL)
                .password("***")
                .authorities(Role.ADMIN.getAuthority())
                .build();
        given(jwtValidator.isValidJWT(any(String.class))).willReturn(true);
        given(jwtValidator.getUserEmail(any(String.class))).willReturn(TEST_USER_EMAIL);
        given(jwtValidator.resolveToken(any(HttpServletRequest.class))).willReturn(AUTH_HEADER.substring(7));
        given(jwtValidator.getAuthentication(any(String.class))).willReturn(new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()));

        this.mvc.perform(delete("/rest/searchbar/delete/{id}", 0)
                        .header("Authorization", this.AUTH_HEADER))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void deleteFoodFromRezeptWorks() throws Exception {
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(TEST_USER_EMAIL)
                .password("***")
                .authorities(Role.ADMIN.getAuthority())
                .build();
        given(jwtValidator.isValidJWT(any(String.class))).willReturn(true);
        given(jwtValidator.getUserEmail(any(String.class))).willReturn(TEST_USER_EMAIL);
        given(jwtValidator.resolveToken(any(HttpServletRequest.class))).willReturn(AUTH_HEADER.substring(7));
        given(jwtValidator.getAuthentication(any(String.class))).willReturn(new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()));

        Rezepte r = new Rezepte("sandra", "Fleisch A", 4, 2, 2, Menueart.FRÜHSTÜCK, false, false, foods, uv);
        given(this.rezepteService.deleteFoodFromRezept(0,0)).willReturn(r);
        LOG.info(""+r.getId());
        LOG.info(r.getName());
        LOG.info(""+r.getFoods().get(0).getId());
        LOG.info(""+r.getFoods().get(0).getName());
        this.mvc.perform(delete("/rest/searchbar/{rId}/deleteF/{fId}",0,0)
                .header("Authorization", this.AUTH_HEADER))
                .andDo(print())
                .andExpect(status().isOk());
        Mockito.verify(rezepteService,Mockito.times(1)).deleteFoodFromRezept(0,0);

    }
}

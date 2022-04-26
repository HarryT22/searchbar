package com.searchbar.sweng.searchbar;

import com.searchbar.sweng.searchbar.inbound.RezepteController;
import com.searchbar.sweng.searchbar.model.Menueart;
import com.searchbar.sweng.searchbar.model.Rezepte;
import com.searchbar.sweng.searchbar.model.RezepteService;
import com.searchbar.sweng.searchbar.model.Unvertraeglichkeiten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RezepteController.class)
public class RezepteControllerTests {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private RezepteService rezepteService;
    private Unvertraeglichkeiten uv;
    private HashMap<String,String> map;
    private Rezepte r;
    @BeforeEach
    public void setUp() throws Exception{
        this.map = new HashMap<>();
        map.put("Senf","200ML");
        this.uv = new Unvertraeglichkeiten(1,false,false,false);
        this.r = new Rezepte(1,"Fleisch A",2,3,5,2, Menueart.MITTAGESSEN,false,false,map,uv);
    }
    @Test
    public void listNormal()throws Exception{
        List<Rezepte> result = new ArrayList<>();
        result.add(r);
        given(this.rezepteService.listNormal("Fleisch",false,false,false,false,false)).willReturn(result);

    }
}

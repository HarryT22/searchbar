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
    private HashMap<Food,String> map;
    private Rezepte r;
    private Food f;



    @BeforeEach
    public void setUp() throws Exception{

    }
    @Test
    public void listNormal()throws Exception{
        List<Rezepte> result = new ArrayList<>();
        result.add(r);
        given(this.rezepteService.listNormal("Fleisch",false,false,false,false,false)).willReturn(result);
        this.mvc.perform(get("/free/{name}/{f}/{l}/{h}/{vegan}/{vegetarisch}","Fleisch",false,false,false,false,false))
                .andDo(print());
    }
}

package com.searchbar.sweng.searchbar;

import com.searchbar.sweng.searchbar.inbound.RezepteController;
import com.searchbar.sweng.searchbar.model.RezepteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = RezepteController.class)
public class RezepteControllerTests {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private RezepteService rezepteService;
}

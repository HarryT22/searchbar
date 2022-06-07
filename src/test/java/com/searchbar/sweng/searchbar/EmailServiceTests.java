package com.searchbar.sweng.searchbar;

import com.searchbar.sweng.searchbar.model.Food;
import com.searchbar.sweng.searchbar.model.Menueart;
import com.searchbar.sweng.searchbar.model.Repositories.RezepteRepository;
import com.searchbar.sweng.searchbar.model.Rezepte;
import com.searchbar.sweng.searchbar.model.Service.EmailService;
import com.searchbar.sweng.searchbar.model.Unvertraeglichkeiten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTests {
    @Mock
    RezepteRepository rezepteRepository;
    @Mock
    JavaMailSender javaMailSender;

    EmailService emailService;
    Rezepte vegan;
    String address;
    String name;
    ArrayList<Food> empty;
    Unvertraeglichkeiten neutral;
    Food leer;

    @BeforeEach
    public void setUp() throws Exception{
        this.emailService = new EmailService(this.rezepteRepository,this.javaMailSender);
        this.address = "ht900929@fh-muenster.de";
        this.name = "Harry T";
        this.empty = new ArrayList<>();
        this.neutral = new Unvertraeglichkeiten(true,true,true);
        this.leer = new Food("Kalorien Mittel",200,200,"400Gramm");
        empty.add(leer);
        this.vegan = new Rezepte("A",1,"A Vegan",2,4,2, Menueart.FRÜHSTÜCK,false,true,empty,neutral);
    }

    @Test
    public void rezeptOfTheDayWorks(){
        given(rezepteRepository.findFirstByOrderByIdDesc()).willReturn(vegan);
        this.emailService.rezeptOfTheDay(address,name);
        verify(rezepteRepository, Mockito.times(1)).findFirstByOrderByIdDesc();
    }
}

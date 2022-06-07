package com.searchbar.sweng.searchbar;

import com.searchbar.sweng.searchbar.model.Event.EventPublisher;
import com.searchbar.sweng.searchbar.model.Exceptions.MessageNotSendException;
import com.searchbar.sweng.searchbar.model.Exceptions.NoSuchRecipieException;
import com.searchbar.sweng.searchbar.model.Repositories.FoodRepository;
import com.searchbar.sweng.searchbar.model.Service.RezepteService;
import org.junit.jupiter.api.Assertions;
import com.searchbar.sweng.searchbar.model.*;
import com.searchbar.sweng.searchbar.model.Repositories.RezepteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import java.util.*;

import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class RezepteServiceTests {

    @Mock
    RezepteRepository rezepteRepository;

    @Mock
    FoodRepository foodRepository;

    RezepteService rezepteService;

    @Mock
    EventPublisher eventPublisher;

    ArrayList<Food> foods;
    Unvertraeglichkeiten uv;
    Food food;
    Rezepte r;
    ArrayList<Rezepte> test;

    @BeforeEach
    public void setUp() throws Exception {
        this.rezepteService = new RezepteService(this.rezepteRepository, this.foodRepository,this.eventPublisher);
        this.foods = new ArrayList<>();
        this.uv = new Unvertraeglichkeiten(false,false,false);
        this.food = new Food("Fleisch",200,400,"400 Gramm");
        this.foods.add(food);
        this.r = new Rezepte("Sandra",101,"test",2,4,2, Menueart.FRÜHSTÜCK,false,false,foods,uv);
        this.test = new ArrayList<>();
    }

    /**
     * Tests if the listNormal method filters the intolerances and preferences correctly.
     * Also tests the role based amount of recipes returned by the method.
     */
    @Test
    public void listNormalWorks() {
        List<Rezepte> test = new ArrayList<>();
        ArrayList<Food> hochK = new ArrayList<>();
        ArrayList<Food> niedrigK = new ArrayList<>();
        ArrayList<Food> hochP = new ArrayList<>();
        ArrayList<Food> niedrigP = new ArrayList<>();
        ArrayList<Food> empty = new ArrayList<>();
        Unvertraeglichkeiten histamine = new Unvertraeglichkeiten(false,true,true);
        Unvertraeglichkeiten fructose = new Unvertraeglichkeiten(true,false,true);
        Unvertraeglichkeiten lactose = new Unvertraeglichkeiten(true,true,false);
        Unvertraeglichkeiten neutral = new Unvertraeglichkeiten(true,true,true);
        Food vielK = new Food("Kalorien Bombe",200,1200,"400Gramm");
        Food vielP = new Food("Protein Bombe",1200,200,"400Gramm");
        Food wenigK = new Food("Kalorien Arm",200,0,"400Gramm");
        Food wenigP = new Food("Protein Arm",0,200,"400Gramm");
        Food leer = new Food("Kalorien Mittel",200,200,"400Gramm");
        hochK.add(vielK);
        hochP.add(vielP);
        niedrigK.add(wenigK);
        niedrigP.add(wenigP);
        empty.add(leer);
        Rezepte vegan = new Rezepte("A",1,"A Vegan",2,4,2,Menueart.FRÜHSTÜCK,false,true,empty,neutral);
        Rezepte vegetarisch =new Rezepte("A",2,"A Vegetarisch",2,4,2,Menueart.FRÜHSTÜCK,true,false,empty,neutral);
        Rezepte hista = new Rezepte("A",3,"A Histamine",2,4,2,Menueart.FRÜHSTÜCK,true,true,empty,histamine);
        Rezepte fruc = new Rezepte("A",4,"A Fructose",2,4,2,Menueart.FRÜHSTÜCK,true,true,empty,fructose);
        Rezepte lac = new Rezepte("A",5,"A Lactose",2,4,2,Menueart.FRÜHSTÜCK,true,true,empty,lactose);
        Rezepte kaloW = new Rezepte("A",6,"A MinK",2,4,2,Menueart.FRÜHSTÜCK,true,true,niedrigK,neutral);
        Rezepte kaloV = new Rezepte("A",7,"A MaxK",2,4,2,Menueart.FRÜHSTÜCK,true,true,hochK,neutral);
        Rezepte proW = new Rezepte("A",8,"A MinP",2,4,2,Menueart.FRÜHSTÜCK,true,true,niedrigP,neutral);
        Rezepte proV = new Rezepte("A",9,"A MaxP",2,4,2,Menueart.FRÜHSTÜCK,true,true,hochP,neutral);
        Rezepte res1 = new Rezepte("A",10,"A FILLTERPASSED", 2,4,2,Menueart.FRÜHSTÜCK,true,true,empty,neutral);
        Rezepte res2 = new Rezepte("A",11,"A FILLTERPASSED", 2,4,2,Menueart.FRÜHSTÜCK,true,true,empty,neutral);
        Rezepte res3 = new Rezepte("A",12,"A FILLTERPASSED", 2,4,2,Menueart.FRÜHSTÜCK,true,true,empty,neutral);
        Rezepte res4 = new Rezepte("A",13,"A FILLTERPASSED", 2,4,2,Menueart.FRÜHSTÜCK,true,true,empty,neutral);
        Rezepte res5 = new Rezepte("A",14,"A FILLTERPASSED", 2,4,2,Menueart.FRÜHSTÜCK,true,true,empty,neutral);
        Rezepte res6 = new Rezepte("A",15,"A FILLTERPASSED", 2,4,2,Menueart.FRÜHSTÜCK,true,true,empty,neutral);

        test.add(vegan);
        test.add(vegetarisch);
        test.add(hista);
        test.add(fruc);
        test.add(lac);
        test.add(kaloW);
        test.add(kaloV);
        test.add(proW);
        test.add(proV);
        test.add(res1);
        given(rezepteRepository.findByName("A")).willReturn(test);
        List<Rezepte> results = this.rezepteService.listNormal("NORMAL","A",true,true,true,true,true,100,1000,100,1000);
        assertThat(results.size(), is(1));
        assertThat(results.get(0).getId(), is(10));
        assertThat(results.get(0).getName(), is("A FILLTERPASSED"));
        test.add(res2);
        test.add(res3);
        test.add(res4);
        test.add(res5);
        test.add(res6);
        List<Rezepte> results2 = this.rezepteService.listNormal("NORMAL","A",true,true,true,true,true,100,1000,100,1000);
        assertThat(results2.size(), is(5));
        List<Rezepte> results3 = this.rezepteService.listNormal("ADMIN","A",true,true,true,true,true,100,1000,100,1000);
        assertThat(results3.size(), is(6));

    }

    /**
     * Tests if the method throws the correct exception when the list is empty.
     */
    @Test
    public void listNormalException() {
        ArrayList<Rezepte> results = new ArrayList<>();
        given(rezepteRepository.findByName("TEST")).willReturn(results);
        Assertions.assertThrows(NoSuchRecipieException.class,() -> {
            rezepteService.listNormal("NORMAL","TEST",false,false,false,false,false,500,1000,500,1000);
        });
    }

    /**
     * Tests if the method throws the correct exception when the list has something in it.
     */
    @Test
    public void listNormalException2() {
        ArrayList<Rezepte> results = new ArrayList<>();
        results.add(r);
        given(rezepteRepository.findByName("test")).willReturn(results);
        Assertions.assertThrows(NoSuchRecipieException.class,() -> {
            rezepteService.listNormal("NORMAL","test",false,false,false,false,false,500,1000,500,1000);
        });
    }

    /**
     * Tests that you can save a recipe correctly.
     */
    @Test
    public void saveRezeptWorks() {
        given(this.eventPublisher.publishEvent(ArgumentMatchers.any())).willReturn(true);
        Rezepte t = this.rezepteService.saveRezept("Sandra","test",2,4,2,Menueart.FRÜHSTÜCK,false,false,false,false,false,"");
        assertThat(t.getAuthor(), is ("Sandra"));
        assertThat(t.getName(), is ("test"));
        assertThat(t.getArbeitszeit(), is (2));
        assertThat(t.getKochzeit(), is (4));
        assertThat(t.getPortionen(), is(2));
        assertThat(t.getMenueart(), is (Menueart.FRÜHSTÜCK));
        assertThat(t.isVegan(), is(false));
        assertThat(t.isVegetarisch(), is(false));
        assertThat(t.getUnvertraeglichkeiten().isFructose(), is(false));
        assertThat(t.getUnvertraeglichkeiten().isLactose(), is(false));
        assertThat(t.getUnvertraeglichkeiten().isHistamine(), is(false));
        assertThat(t.getImage(), is(""));
    }

    /**
     * Tests if the method returns the correct recipe with the foods added in the foods list.
     */
    @Test
    public void addFoodToRezeptWorks() {
        List<Food> foods = new ArrayList<>();
        Rezepte t = new Rezepte("Sandra",100,"test",2,4,2, Menueart.FRÜHSTÜCK,false,false,foods,uv,"");
        given(rezepteRepository.findById(100)).willReturn(Optional.of(t));
        Rezepte result = this.rezepteService.addFoodToRezept("Kleisch",200,400,"400G",100);
        assertThat(result.getId(),is(100));
        assertThat(result.getAuthor(), is ("Sandra"));
        assertThat(result.getName(), is ("test"));
        assertThat(result.getArbeitszeit(), is (2));
        assertThat(result.getKochzeit(), is (4));
        assertThat(result.getPortionen(), is(2));
        assertThat(result.getMenueart(), is (Menueart.FRÜHSTÜCK));
        assertThat(result.isVegan(), is(false));
        assertThat(result.isVegetarisch(), is(false));
        assertThat(result.getUnvertraeglichkeiten().isFructose(), is(false));
        assertThat(result.getUnvertraeglichkeiten().isLactose(), is(false));
        assertThat(result.getUnvertraeglichkeiten().isHistamine(), is(false));
        assertThat(result.getImage(), is(""));
        assertThat(result.getFoods().get(0).getName(), is("Kleisch"));
        assertThat(result.getFoods().get(0).getProteine(), is(200));
        assertThat(result.getFoods().get(0).getKalorien(), is(400));
        assertThat(result.getFoods().get(0).getMenge(), is("400G"));
    }

    /**
     * Tests if the method throws the correct exception if the recipe does not exist.
     */
    @Test
    public void addFoodToRezeptException() {
        given(rezepteRepository.findById(2000)).willReturn(Optional.empty());
        Assertions.assertThrows(NoSuchRecipieException.class,() -> {
            Rezepte r = rezepteService.addFoodToRezept("Quark",200,400,"400G",2000);
        });
    }

    /**
     * Tests if the method deletes the recipe correctly.
     */
    @Test
    public void shouldCanDeleteRezept() {
        Rezepte r2 = new Rezepte();
        r2.setId(20);
        given(this.rezepteRepository.findById(20)).willReturn(Optional.of(r2));
        this.rezepteService.deleteRezept(20);
        verify(rezepteRepository, Mockito.times(1)).delete(r2);
    }

    /**
     * Tests if the method throws the correct exception when the list is empty.
     */
    @Test
    public void shouldCanDeleteRezeptException() {
        given(rezepteRepository.findById(2000)).willReturn(Optional.empty());
        Assertions.assertThrows(NoSuchRecipieException.class,() -> {
            rezepteService.deleteRezept(2000);
        });
    }

    /**
     * Tests if the method deletes the correct food from the specified recipe
     */
    @Test
    public void shouldCanDelteFoodFromRezept() {
        Unvertraeglichkeiten testuv = new Unvertraeglichkeiten(false,true,true);

        Food wenigP = new Food(1,"Protein Arm",0,200,"400Gramm");
        Food leer = new Food(2,"Kalorien Mittel",200,200,"400Gramm");
        ArrayList<Food> foods = new ArrayList<>();
        foods.add(wenigP);
        foods.add(leer);

        Rezepte test = new Rezepte("A",15,"DELETEF", 2,4,2,Menueart.FRÜHSTÜCK,true,true,foods,testuv);

        given(this.rezepteRepository.findById(15)).willReturn(Optional.of(test));
        given(this.foodRepository.findById(1)).willReturn(Optional.of(wenigP));

        this.rezepteService.deleteFoodFromRezept(15,1);

        assertThat(test.getFoods().size(), is(1));
        assertThat(test.getFoods().get(0).getName(), is("Kalorien Mittel"));
        assertThat(test.getFoods().get(0).getId(), is(2));
    }

    /**
     * Tests if the exception is thrown and if it is the correct exception.
     */
    @Test
    public void saveRezeptException() {
        given(this.eventPublisher.publishEvent(ArgumentMatchers.any())).willReturn(false);
        Assertions.assertThrows(MessageNotSendException.class,() -> {
            this.rezepteService.saveRezept("Sandra", "test", 2, 4, 2, Menueart.FRÜHSTÜCK, false, false, false, false, false, "");
        });

    }
}

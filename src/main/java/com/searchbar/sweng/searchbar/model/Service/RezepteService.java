package com.searchbar.sweng.searchbar.model.Service;

import com.searchbar.sweng.searchbar.model.Exceptions.NoSuchRecipieException;
import com.searchbar.sweng.searchbar.model.Exceptions.ResourceNotFoundException;
import com.searchbar.sweng.searchbar.model.Food;
import com.searchbar.sweng.searchbar.model.Menueart;
import com.searchbar.sweng.searchbar.model.Repositories.FoodRepository;
import com.searchbar.sweng.searchbar.model.Repositories.RezepteRepository;
import com.searchbar.sweng.searchbar.model.Rezepte;
import com.searchbar.sweng.searchbar.model.Unvertraeglichkeiten;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RezepteService {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private RezepteRepository rezepteRepository;
    private FoodRepository foodRepository;

    @Autowired
    public RezepteService(RezepteRepository rezepteRepository,FoodRepository foodRepository) {
        this.rezepteRepository = rezepteRepository;
        this.foodRepository = foodRepository;
    }

    /**
     * Search for recipies with certain filters
     * @param role role of the user that is using this method
     * @param name keyword for search
     * @param fructose fructose for intolerance filter
     * @param lactose lactose for intolerance filter
     * @param histamine histamine for intolerance filter
     * @param isVegan for preference filter
     * @param isVegetarisch for preference filter
     * @param minK minimum calories of the recipie
     * @param maxK maximum calories of the recipie
     * @param minP minimum proteins of the recipie
     * @param maxP maximum proteins of the recipie
     * @return list of filtered recipies
     */
    @Transactional(readOnly=true)
    public List<Rezepte> listNormal(String role, String name, boolean fructose, boolean lactose, boolean histamine, boolean isVegan,
                                    boolean isVegetarisch, int minK, int maxK, int minP, int maxP) {
        LOG.info("Execute listNormal({},{}).", role,name);
        List<Rezepte> rezepte = rezepteRepository.findByName(name);
        List<Rezepte> toRemove = new ArrayList<>();
        if (!rezepte.isEmpty()) {
            for (Rezepte r : rezepte) {
                if (isVegan && !r.isVegan()) {
                    LOG.info("Delete recipie {} vegan",r.getName());
                    toRemove.add(r);
                    continue;
                }
                if (isVegetarisch && !r.isVegetarisch()) {
                    LOG.info("Delete recipie {} vegetarisch",r.getName());
                    toRemove.add(r);
                    continue;
                }
                if (fructose && !r.getUnvertraeglichkeiten().isFructose()) {
                    LOG.info("Delete recipie {} fructose",r.getName());
                    toRemove.add(r);
                    continue;
                }
                if (lactose && !r.getUnvertraeglichkeiten().isLactose()) {
                    LOG.info("Delete recipie {} lactose",r.getName());
                    toRemove.add(r);
                    continue;
                }
                if (histamine && !r.getUnvertraeglichkeiten().isHistamine()) {
                    LOG.info("Delete recipie {} histamien",r.getName());
                    toRemove.add(r);
                    continue;
                }
                if (minK >= r.getCalories()) {
                    LOG.info("Delete recipie {} calorie min",r.getName());
                    toRemove.add(r);
                    continue;
                }
                if (maxK <= r.getCalories()) {
                    LOG.info("Delete recipie {} calorie max",r.getName());
                    toRemove.add(r);
                    continue;
                }
                if (minP >= r.getProteins()) {
                    LOG.info("Delete recipie {} protein min",r.getName());
                    toRemove.add(r);
                    continue;
                }
                if (maxP <= r.getProteins()) {
                    LOG.info("Delete recipie {} protein max",r.getName());
                    toRemove.add(r);
                }
            }
            LOG.info("Preference and intolerance filters passed.");
            rezepte.removeAll(toRemove);
            if(role.equals("NORMAL")) {
                if (rezepte.size() >= 5) {
                    LOG.info("Returned {} recipies, for NORMAL user.",rezepte.size());
                    return rezepte.stream().limit(5).collect(Collectors.toList());
                }
            }
            if(role.equals("ADMIN")||role.equals("PREMIUM")){
                if (rezepte.size() >= 10) {
                    LOG.info("Returned {} recipies for PREMIUM OR ADMIN.",rezepte.size());
                    return rezepte.stream().limit(10).collect(Collectors.toList());
                }
            }
            if (rezepte.isEmpty()) {
                LOG.info("After filtering no recipies left.");
                throw new NoSuchRecipieException("Ein Rezept mit diesen Filtern existiert nicht");
            }
            LOG.info("Returned {} recipies.",rezepte.size());
            return rezepte;
        } else {
            LOG.info("Theres no recipie for this keyword.");
            throw new NoSuchRecipieException("Ein solches Rezept existiert nicht.");
        }
    }

    /**
     * Save a recipie with an image
     * @param author  email of the author of this recipie
     * @param rezeptName name of the recipie
     * @param arbeitszeit working time
     * @param kochzeit cooking time
     * @param portionen amount of portions
     * @param menueart (FRÜHSTÜCK,MITTAGESSEN,ABENDESSEN)
     * @param isVegan for preference filter
     * @param isVegetarisch for preference filter
     * @param h histamine for intolerance filter
     * @param l lactose for intolerance filter
     * @param f fructose for intolerance filter
     * @param file image of the recipie
     * @return the recipie construct
     */
    @Transactional
    public Rezepte saveRezept(String author,String rezeptName, int arbeitszeit, int kochzeit, int portionen, Menueart menueart,
                           boolean isVegan, boolean isVegetarisch, boolean h, boolean l, boolean f, String file) {
        LOG.info("Execute saveRezept for {}",rezeptName);
        ArrayList<Food> list = new ArrayList<>();
        Unvertraeglichkeiten uv = new Unvertraeglichkeiten(h,f,l);
        Rezepte r = new Rezepte(author,rezeptName,arbeitszeit,kochzeit,portionen,menueart,isVegan,isVegetarisch,list,uv,file);
        LOG.info("Succesfully added recipie {} ID IS {}.",rezeptName);
        return r;
    }

    /**
     * Add a food to the food-list of a recipie
     * @param name name of the food item
     * @param proteine amount of proteine of the food
     * @param kalorien amount of calories of the food
     * @param id id of the recipie the food is getting added to
     * @return the updated recipie
     */
    @Transactional
    public Rezepte addFoodToRezept(String name, int proteine, int kalorien, String menge, int id) {
        LOG.info("Execute addFoodToRezept with parameters {},{},{},{},{}.",name,proteine,kalorien,menge,id);
        Food f = new Food(name, proteine, kalorien, menge);
        Optional<Rezepte> rOptional = rezepteRepository.findById(id);
        if (rOptional.isPresent()) {
            Rezepte r = rOptional.get();
            List<Food> fl = r.getFoods();
            fl.add(f);
            r.setFoods(fl);
            LOG.info("Successfully added foods.");
            return r;
        } else {
            LOG.info("Recipie with id {} does not exist.",id);
            throw new NoSuchRecipieException("No recipie found");
        }
    }

    @Transactional
    public Rezepte deleteFoodFromRezept(int rId,int fId) {
        LOG.info("Execute deleteFoodFromRezept with parameters rId{} fId{}.",rId,fId);
        Optional<Rezepte> rOptional = rezepteRepository.findById(rId);
        if (rOptional.isPresent()) {
            Rezepte r = rOptional.get();
            List<Food> fl = r.getFoods();
            Optional<Food> f = foodRepository.findById(fId);
            if (!f.isPresent()){
                throw new ResourceNotFoundException("No food with that id found.");
            }
            fl.remove(f.get());
            r.setFoods(fl);
            LOG.info("Successfully deleted foods.");
            return r;
        } else {
            LOG.info("Recipie with id {} does not exist.",rId);
            throw new NoSuchRecipieException("No recipie found");
        }
    }

    /**
     * Delete a recipie from the database.
     * @param id Id of the recipie that is supposed to be deleted.
     */
    @Transactional
    public void deleteRezept(int id) {
        LOG.info("Execute deleteRezept({}).", id);
        Optional<Rezepte> rezepteOptional = rezepteRepository.findById(id);
        if (rezepteOptional.isPresent()) {
            Rezepte r = rezepteOptional.get();
            LOG.info("Successfully deleted recipie with id ({}).", id);
            rezepteRepository.delete(r);
        } else {
            LOG.info("No recipie with this id {}.", id);
            throw new NoSuchRecipieException("This Recipie does not exist");
        }
    }
}


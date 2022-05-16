package com.searchbar.sweng.searchbar.model;

import com.searchbar.sweng.searchbar.model.Exceptions.NoSuchRecipieException;
import com.searchbar.sweng.searchbar.model.Exceptions.NonValidFileException;
import com.searchbar.sweng.searchbar.model.Repositories.RezepteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class RezepteService {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private RezepteRepository rezepteRepository;

    @Autowired
    public RezepteService(RezepteRepository rezepteRepository) {
        this.rezepteRepository = rezepteRepository;
    }

    public List<Rezepte> listNormal(String name, boolean fructose, boolean lactose, boolean histamine, boolean isVegan,
                                    boolean isVegetarisch, int minK, int maxK, int minP, int maxP) {
        List<Rezepte> rezepte = rezepteRepository.findByName(name);
        if (!rezepte.isEmpty()) {
            for (Rezepte r : rezepte) {
                if (isVegan && !r.isVegan()) {
                    rezepte.remove(r);
                    continue;
                }
                if (isVegetarisch && !r.isVegetarisch()) {
                    rezepte.remove(r);
                    continue;
                }
                if (fructose && !r.getUnvertraeglichkeiten().isFructose()) {
                    rezepte.remove(r);
                    continue;
                }
                if (lactose && !r.getUnvertraeglichkeiten().isLactose()) {
                    rezepte.remove(r);
                    continue;
                }
                if (histamine && !r.getUnvertraeglichkeiten().isHistamine()) {
                    rezepte.remove(r);
                    continue;
                }
                if (minK >= r.getCalories()) {
                    rezepte.remove(r);
                    continue;
                }
                if (maxK <= r.getCalories()) {
                    rezepte.remove(r);
                    continue;
                }
                if (minP >= r.getProteins()) {
                    rezepte.remove(r);
                    continue;
                }
                if (maxP <= r.getProteins()) {
                    rezepte.remove(r);
                }
            }
            if (rezepte.size() >= 5) {
                return rezepte.stream().limit(5).collect(Collectors.toList());
            }
            if (rezepte.isEmpty()) {
                throw new NoSuchRecipieException("Ein Rezept mit diesen Filtern existiert nicht");
            }
            return rezepte;
        } else {
            throw new NoSuchRecipieException("Ein solches Rezept existiert nicht.");
        }
    }

    public void saveRezept(@NotNull MultipartFile file, String rezeptName, int arbeitszeit, int kochzeit, int portionen, Menueart menueart,
                           boolean isVegan, boolean isVegetarisch, boolean h, boolean l, boolean f) {
        Rezepte r = new Rezepte();
        Unvertraeglichkeiten uv = new Unvertraeglichkeiten();
        if (file.getOriginalFilename() != null) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if (fileName.contains("..")) {
                throw new NonValidFileException("Not a valid file");
            }
        }
        try {
            r.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        uv.setFructose(f);
        uv.setLactose(l);
        uv.setHistamine(h);
        r.setName(rezeptName);
        r.setArbeitszeit(arbeitszeit);
        r.setKochzeit(kochzeit);
        r.setPortionen(portionen);
        r.setMenueart(menueart);
        r.setVegan(isVegan);
        r.setVegetarisch(isVegetarisch);
        r.setUnvertraeglichkeiten(uv);
        rezepteRepository.save(r);
    }

    public void addFoodToRezept(String name, int proteine, int kalorien, String menge, int id) {
        Food f = new Food(name, proteine, kalorien, menge);
        Optional<Rezepte> rOptional = rezepteRepository.findById(id);
        if (rOptional.isPresent()) {
            Rezepte r = rOptional.get();
            List<Food> fl = r.getFoods();
            fl.add(f);
            r.setFoods(fl);
            rezepteRepository.save(r);
        } else {
            throw new NoSuchRecipieException("No Recipie found");
        }
    }

    public void deleteRezept(int id) {
        Optional<Rezepte> rezepteOptional = rezepteRepository.findById(id);
        if (rezepteOptional.isPresent()) {
            Rezepte r = rezepteOptional.get();
            rezepteRepository.delete(r);
        } else {
            throw new NoSuchRecipieException("This Recipie does not exist");
        }
    }
}


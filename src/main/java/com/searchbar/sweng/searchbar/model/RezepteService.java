package com.searchbar.sweng.searchbar.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RezepteService {
    private RezepteRepository rezepteRepository;
    @Autowired
    public RezepteService(RezepteRepository rezepteRepository){
        this.rezepteRepository = rezepteRepository;
    }

}

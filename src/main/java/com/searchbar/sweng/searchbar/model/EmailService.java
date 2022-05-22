package com.searchbar.sweng.searchbar.model;

import com.searchbar.sweng.searchbar.model.Exceptions.ResourceNotFoundException;
import com.searchbar.sweng.searchbar.model.Repositories.RezepteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private RezepteRepository rezepteRepository;

    @Autowired
    public EmailService (JavaMailSender javaMailSender,RezepteRepository rezepteRepository) {
        this.javaMailSender = javaMailSender;
        this.rezepteRepository = rezepteRepository;
    }

    public void rezeptOfTheDay(String address, String name) {
        LOGGER.info("Execute rezeptOfTheDay({},{}).",address, name);
        Rezepte r = rezepteRepository.findFirstByOrderByIdDesc();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(address);
        message.setSubject("Welcome to the NutriApp!");
        message.setText(String.format("""
                Dear %s,
                                        
                you successfully registered for the best Nutrition App of the World!
                
                !!RECIPIE OF THE DAY!!
                Name: %s
                Arbeitszeit: %o
                Kochzeit: %o
                Gesamtzeit: %o
                Portionen: %o
                Menüart: %s
                Vegetarisch: %b
                Vegan: %b
                Histamine: %b
                Fructose: %b
                Lactose: %b
                Kalorien: %o
                Proteine: %o
                                        
                Best regards
                The NutriApp Team
                """, name,r.getName(),r.getArbeitszeit(),r.getKochzeit(),r.getGesamtzeit(),r.getPortionen(),r.getMenueart(),r.isVegetarisch(),
                r.isVegan(),r.getUnvertraeglichkeiten().isHistamine(),r.getUnvertraeglichkeiten().isFructose(),r.getUnvertraeglichkeiten().isLactose(),
                r.getCalories(),r.getProteins()));
        message.setFrom("donotreply@fh-muenster.de");
        javaMailSender.send(message);
        LOGGER.info("Welcome email sent to: {}", address);
    }

}

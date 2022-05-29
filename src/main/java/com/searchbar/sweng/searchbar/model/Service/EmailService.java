package com.searchbar.sweng.searchbar.model.Service;

import com.searchbar.sweng.searchbar.model.Repositories.RezepteRepository;
import com.searchbar.sweng.searchbar.model.Rezepte;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EmailService {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private RezepteRepository rezepteRepository;
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService (RezepteRepository rezepteRepository, JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
        this.rezepteRepository = rezepteRepository;
    }

    /**
     * Send the last added recipie to a newly registered user
     * @param name name of the new user
     * @param address email address of the user
     */
    @Transactional(readOnly=true)
    public void rezeptOfTheDay(String address, String name) {
        LOGGER.info("Execute rezeptOfTheDay({},{}).",address, name);
        Rezepte r = rezepteRepository.findFirstByOrderByIdDesc();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("ht900929@fh-muenster.de");
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
        System.out.print(message);
        javaMailSender.send(message);
        LOGGER.info("Welcome email sent to: {}", address);
    }
}

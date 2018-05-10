package com.example.phone.config;

import java.math.BigDecimal;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Component;

import com.example.phone.dto.Phone;
import com.example.phone.repository.PhoneRepository;

import reactor.core.publisher.Flux;

@Component
public class DataAppInitializr {

	private final PhoneRepository phoneRepository;

    @org.springframework.context.event.EventListener(ApplicationReadyEvent.class)
    public void run(ApplicationReadyEvent evt) {

        this.phoneRepository
                .deleteAll()
                .thenMany(Flux.just(new Phone(new Long(1), new Long(1), "Samsung Galaxy S9", "Movil con 6 pulgadas de pantalla FullHD, 4 GB RAM, 64 GB ROM", new BigDecimal("1000")), 
                		new Phone(new Long(2), new Long(2), "Iphone 9", "Movil con 6 pulgadas de pantalla, 4 GB RAM, 128 GB ROM", new BigDecimal("950")),
                		new Phone(new Long(3), new Long(3), "Xiaomi MI 5", "Movil con 5 pulgadas de pantalla FullHD, 3 GB RAM, 32 GB ROM", new BigDecimal("500.99")),
                		new Phone(new Long(4), new Long(4), "LG G7", "Movil con 5 pulgadas de pantalla FullHD, 3 GB RAM, 32 GB ROM", new BigDecimal("600.35"))
                		)
                		).flatMap(phone -> this.phoneRepository.save(phone))
                		.subscribe(null, null,
                        () -> this.phoneRepository.findAll().subscribe(System.out::println));
                

    }

    DataAppInitializr(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }
	
}

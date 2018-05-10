package com.example.phone.service;


import com.example.phone.dto.Phone;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface PhoneService {

	public Flux<Phone> getPhones();
	
	public Mono<Phone> findById(Long id);
	
}

package com.example.phone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.phone.dto.Phone;
import com.example.phone.service.PhoneService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.http.MediaType;


@RestController
public class PhoneController {

	@Autowired
	PhoneService phoneService;
	
	
	@RequestMapping(value = "v1/phone", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	Flux<Phone> phoneList() {
		return phoneService.getPhones();
	}
	
	@RequestMapping(value = "v1/phone/{id}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	Mono<Phone> findById(@PathVariable Long id){
		return phoneService.findById(id);
	}
	
}

package com.example.phone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.phone.dto.Phone;
import com.example.phone.repository.PhoneRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PhoneServiceImpl implements PhoneService{

	@Autowired
	private PhoneRepository phoneRepository;
	
	public Flux<Phone> getPhones(){
		return phoneRepository.findAll();
	}
	
	public Mono<Phone> findById(Long id){
		return phoneRepository.findById(id);
	}
	
}

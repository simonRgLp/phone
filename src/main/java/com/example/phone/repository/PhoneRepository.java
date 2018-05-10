package com.example.phone.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.phone.dto.Phone;

public interface PhoneRepository extends ReactiveMongoRepository<Phone, Long>{

}

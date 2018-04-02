package com.berkum.spring.paging.business.api;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.berkum.spring.paging.domain.Person;

public interface PersonRepository extends MongoRepository<Person, Long> {

}

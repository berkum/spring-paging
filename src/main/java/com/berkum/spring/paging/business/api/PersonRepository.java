package com.berkum.spring.paging.business.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.berkum.spring.paging.domain.Person;

@RepositoryRestResource
public interface PersonRepository extends MongoRepository<Person, Long> {

	Page<Person> findByLastName(Pageable page, String lastName);

}

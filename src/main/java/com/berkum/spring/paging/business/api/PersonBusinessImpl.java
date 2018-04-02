package com.berkum.spring.paging.business.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.berkum.spring.paging.domain.Person;

@Component
public class PersonBusinessImpl implements PersonBusiness {

	@Autowired 
	PersonRepository repository;
	
	
	public Page<Person> findAll(Pageable page) {
		Page<Person> persons = repository.findAll(page);
		return persons;
	}

}

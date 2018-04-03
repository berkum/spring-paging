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
	
	@Override
	public Page<Person> findByLastName(Pageable page, String lastName) {
		Page<Person> persons = repository.findByLastName(page, lastName);
		return persons;
	}

	
	public Page<Person> findAll(Pageable page) {
		Page<Person> persons = repository.findAll(page);
		return persons;
	}

}

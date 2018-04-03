package com.berkum.spring.paging.business.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.berkum.spring.paging.domain.Person;

public interface PersonBusiness {

	Page<Person> findAll(Pageable page);

	Page<Person> findByLastName(Pageable pageable, String lastName);
}

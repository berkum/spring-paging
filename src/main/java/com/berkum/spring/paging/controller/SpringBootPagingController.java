package com.berkum.spring.paging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.berkum.spring.paging.business.api.PersonBusiness;
import com.berkum.spring.paging.domain.Person;

@RestController
public class SpringBootPagingController {

	
	@Autowired
	private PersonBusiness business;

	@RequestMapping(value = "/listPageable", method = RequestMethod.GET)
	Page<Person> employeesPageable(Pageable pageable) {
		return business.findAll(pageable);

	}

	@RequestMapping(value = "/listPageableRE", method = RequestMethod.GET)
	ResponseEntity<Page<Person>> employeesPageableRE(Pageable pageable) {
		return ResponseEntity.ok(business.findAll(pageable));

	}
	
	
}

package com.berkum.spring.paging.controller;

import com.berkum.spring.paging.business.api.PersonBusiness;
import com.berkum.spring.paging.domain.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringBootPagingController {

  @Autowired
  private PersonBusiness business;

  // @Bean
  // public PagingModule pagingModule() {
  // return new PagingModule();
  // }

  @RequestMapping(value = "/listPageable", method = RequestMethod.GET)
  Page<Person> employeesPageable(Pageable pageable) {
    Page<Person> persons = business.findAll(pageable);
    return persons;

  }

  @RequestMapping(value = "/listPageableRE", method = RequestMethod.GET)
  ResponseEntity<Page<Person>> employeesPageableRE(Pageable pageable) {
    return ResponseEntity.ok(business.findAll(pageable));
  }

  @RequestMapping(value = "/listPageableLN", method = RequestMethod.GET)
  ResponseEntity<Page<Person>> employeesPageable2(Pageable pageable, @RequestParam("lastName") String lastName) {
    return ResponseEntity.ok(business.findByLastName(pageable, lastName));
  }

}

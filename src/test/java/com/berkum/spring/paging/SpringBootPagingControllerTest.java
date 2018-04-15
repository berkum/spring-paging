package com.berkum.spring.paging;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.berkum.spring.paging.business.api.PersonBusiness;
import com.berkum.spring.paging.controller.SerializablePageImpl;
import com.berkum.spring.paging.controller.SpringBootPagingController;
import com.berkum.spring.paging.domain.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableSpringDataWebSupport
@RunWith(SpringRunner.class)
@ContextConfiguration
// @SpringBootTest
@WebMvcTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class SpringBootPagingControllerTest {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private SpringBootPagingController controller;

	@MockBean
	private PersonBusiness business;

	@Autowired
	private ObjectMapper mapper;

	@Before
	public void setup() throws Exception {
		Page<Person> result = buildResult();

		BDDMockito.given(business.findAll(Mockito.any(Pageable.class))).willReturn(result);

		this.mvc.perform(get("/listPageable")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("totalElements")));

	}

	private Page<Person> buildResult() {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Alain", "Bergeron"));
		persons.add(new Person("Alain-2", "Bergeron"));
		persons.add(new Person("Alain3", "Bergeron"));
		List<Order> orders = new ArrayList<>();
		Order o = new Order(Direction.ASC, "firstName").ignoreCase();
		orders.add(o);
		o = new Order(Direction.DESC, "lastName").ignoreCase();
		orders.add(o);
		Sort s = new Sort(orders);
		PageRequest pr = new PageRequest(0, 3, s);
		PageImpl<Person> result = new PageImpl<>(persons, pr, 30);
		return result;
	}

	@Test
	public void reactsOnGetRequest() throws Exception {
		MvcResult ra = mvc.perform(get("/listPageable")).andExpect(status().isOk()).andReturn();

		SerializablePageImpl<Person> persons = mapper.readValue(ra.getResponse().getContentAsString(),
				new TypeReference<SerializablePageImpl<Person>>() {
				});
		System.err.println("ra " + ra);
		System.err.println("persons " + persons);
		Assert.assertEquals(30, persons.getTotalElements());
		Assert.assertEquals(0, persons.getNumber());
		Assert.assertEquals(3, persons.getNumberOfElements());
		Assert.assertEquals(3, persons.getSize());
		persons.getContent();

		Assert.assertEquals(Direction.ASC, persons.getSort().getOrderFor("firstName").getDirection());
		Assert.assertEquals(Direction.DESC, persons.getSort().getOrderFor("lastName").getDirection());

	}

}

package com.lunchtime.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.lunchtime.LunchTimeWSApplication;
import com.lunchtime.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LunchTimeWSApplication.class)
@ActiveProfiles("test")
@WebAppConfiguration
public class CustomerControllerTest {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	@Transactional
	public void shouldAddCustomerSuccessfully() throws Exception {
		Customer customer = new Customer("Thulio", "thuliolins@gmail.com", "123456");
		Gson gson = new Gson();
		String customerJson = gson.toJson(customer);

		this.mockMvc.perform(post("/customer").contentType(contentType).content(customerJson))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void shouldNotAddDuplicateCustomer() throws Exception {
		Customer customer = new Customer("Thulio", "thuliolins@gmail.com", "123456");
		Gson gson = new Gson();
		String customerJson = gson.toJson(customer);

		this.mockMvc.perform(post("/customer").contentType(contentType).content(customerJson))
				.andExpect(status().isCreated());

		this.mockMvc.perform(post("/customer").contentType(contentType).content(customerJson))
				.andExpect(status().isConflict());
	}
}

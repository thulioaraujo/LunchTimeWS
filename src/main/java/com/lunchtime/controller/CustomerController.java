package com.lunchtime.controller;

import java.net.URI;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.gson.Gson;
import com.lunchtime.exception.DuplicatedUserException;
import com.lunchtime.model.Customer;
import com.lunchtime.service.CustomerService;

/**
 * Specifies methods used to manipulate customer information which is stored in
 * the database.
 * 
 * @author Thúlio Araújo (thuliolins@gmail.com)
 * @since 2017-01-24
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody Customer customer) {

		try {
			customer = this.customerService.createCustomer(customer);
		} catch (ConstraintViolationException exception) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
		}

		if (customer.getCustomerId() != null && customer.getCustomerId() > 0) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{customerId}")
					.buildAndExpand(customer.getCustomerId()).toUri();
			
			return ResponseEntity.created(location).build();
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{customerId}")
	public String readCustomer(@PathVariable Long customerId) {
		Gson gson = new Gson();
		Customer customer = this.customerService.findOne(customerId);
		return gson.toJson(customer);
	}

	@RequestMapping(value = "/logincustomer", method = RequestMethod.GET, headers = "Accept=application/json")
	public String loginCustomer(@RequestParam(value = "customerLogin") String customerLogin,
			@RequestParam(value = "customerPassword") String customerPassword) {
		Gson gson = new Gson();
		Customer customer = this.customerService.loginCustomer(customerLogin, customerPassword);
		return gson.toJson(customer);
	}
}

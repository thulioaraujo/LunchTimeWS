package com.lunchtime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
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

	@RequestMapping(value = "/addcustomer", method = RequestMethod.GET, headers = "Accept=application/json")
	public String createCustomer(@RequestParam(value = "customerName") String customerName,
			@RequestParam(value = "customerLogin") String customerLogin,
			@RequestParam(value = "customerPassword") String customerPassword) {
		Gson gson = new Gson();
		Customer customer = new Customer(customerName, customerLogin, customerPassword);
		customer = this.customerService.createCustomer(customer);
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

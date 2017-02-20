package com.lunchtime.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunchtime.model.Customer;
import com.lunchtime.repository.CustomerRepository;

/**
 * Represents service layer to communicate with the repository
 * 
 * @author Thúlio Araújo (thuliolins@gmail.com)
 * @since 2017-02-08
 * @version	1.0
 */
@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Transactional
	public Customer createCustomer(Customer customer) throws ConstraintViolationException {
		return customerRepository.save(customer);
	}
	
	@Transactional
	public Customer findOne(long customerId) {
		return customerRepository.findOne(customerId);
	}

	@Transactional
	public Customer loginCustomer(String customerLogin, String customerPassword) {
		return customerRepository.loginCustomer(customerLogin, customerPassword);
	}
	
	@Transactional
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}
}

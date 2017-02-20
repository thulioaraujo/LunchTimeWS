package com.lunchtime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lunchtime.model.Customer;

/**
 * Specifies methods used to obtain and modify Customer related information
 * which is stored in the database.
 * 
 * @author Thúlio Araújo (thuliolins@gmail.com)
 * @since 2017-01-24
 * @version 1.0
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	/**
	 * Finds Customer by using the customerLogin and customerPassword as a search criteria.
	 * 
	 * @param customerLogin
	 * @param customerPassword
	 * @return a customer object from database
	 */
	@Query("SELECT c FROM Customer c WHERE (c.customerLogin) = (:customerLogin) AND (c.customerPassword) = (:customerPassword)")
	public Customer loginCustomer(@Param("customerLogin") String customerLogin, @Param("customerPassword") String customerPassword);
}

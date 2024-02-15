package com.cdac.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.Customer;
import com.cdac.exception.CustomerServiceException;
import com.cdac.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public int register(Customer customer) throws CustomerServiceException {
		Long count = customerRepository.findIfCustomerIsPresent(customer.getEmail(),customer.getPassword());
		if (count == 1)
            throw new CustomerServiceException("Customer already registered!");
        else {
            

             customerRepository.save(customer);
            return customer.getId();
        }
	}
	
	public Customer login(String email, String password) throws CustomerServiceException {
		Optional<Customer> customer = customerRepository.findByEmailAndPassword(email, password);
		if(customer.isPresent())
			return customer.get();
		else
			throw new CustomerServiceException("Invalid Email/Password");
	}
	
	

}
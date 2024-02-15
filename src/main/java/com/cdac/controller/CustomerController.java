package com.cdac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.service.CustomerService;
//import com.cdac.util.JwtTokenUtil;
import com.cdac.dto.CustomerLoginStatus;
import com.cdac.dto.LoginDetails;
import com.cdac.dto.RegistrationStatus;
import com.cdac.dto.Status;
import com.cdac.entity.Customer;
import com.cdac.exception.CustomerServiceException;




@RestController
@CrossOrigin
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/customer/register")
	public Status register(@RequestBody Customer customer) {
		try {
			int id = customerService.register(customer);
			RegistrationStatus status = new RegistrationStatus();
			status.setStatus(true);
			status.setMessageIfAny("Registration successful!");
			status.setCustomerId(id);
			return status;
		}
		catch(CustomerServiceException e) {
			Status status = new Status();
			status.setStatus(false);
			status.setMessageIfAny(e.getMessage());
			return status;
		}
	}
	
	 @PostMapping("/customer/login")
	    public Status login(@RequestBody LoginDetails loginDetails) {
	        try {
	            Customer customer = customerService.login(loginDetails.getEmail(), loginDetails.getPassword());
	            CustomerLoginStatus status = new CustomerLoginStatus();
	            status.setStatus(true);
	            status.setMessageIfAny("Login successful!");
	            status.setCustomerId(customer.getId());
	            status.setName(customer.getName());
	            return status;

	            // Generate a token
//	            String token = JwtTokenUtil.generateToken(loginDetails.getEmail());

	            // Return the token in the response
//	            return ResponseEntity.ok(token);
	        } catch (CustomerServiceException e) {
	            // Handle service exception
	            Status status = new Status();
	            status.setStatus(false);
	            status.setMessageIfAny(e.getMessage());
//	            return ResponseEntity.status(401).body(status.getMessageIfAny());
	            return status;
	        }
	    }
	
	}


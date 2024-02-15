package com.cdac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.LoginDetails;
import com.cdac.dto.RegistrationStatus;
import com.cdac.dto.RegistrationStatusAdmin;
import com.cdac.dto.Status;
import com.cdac.entity.Admin;
import com.cdac.entity.Category;
import com.cdac.exception.AdminServiceException;
import com.cdac.repository.CategoryServiceRepository;
import com.cdac.service.AdminService;
import com.cdac.service.CategoryService;

@RestController
@CrossOrigin
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/admin")
	public RegistrationStatusAdmin register(@RequestBody Admin admin){
		try {
			int id= adminService.register(admin);
			
			RegistrationStatusAdmin status = new RegistrationStatusAdmin();
			status.setStatus(true);
			status.setMessage("Admin registered successfully!!");
			status.setId(id);
			return status;
			
		}catch(AdminServiceException e) {
			RegistrationStatusAdmin status = new RegistrationStatusAdmin();
			status.setStatus(false);
			status.setMessage(e.getMessage());
			return status;
			
		}
		
	}
	@PostMapping("/admin/login")
	public ResponseEntity<Status> login(@RequestBody LoginDetails loginRequest) {
	    try {
	        // Assuming you have an authentication method in your service
	        Admin admin = adminService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

	        // If authentication is successful, you might generate a token for further authorization
//	        String authToken = generateAuthToken(admin);

	        // Respond with success and the generated token
	        Status status = new Status();
	        status.setStatus(true);
	        status.setMessageIfAny("Login successful");
//	        status.setAuthToken(authToken);
	        return ResponseEntity.ok(status);

	    } catch (AdminServiceException e) {
	        // If authentication fails, respond with an error status
	        Status status = new Status();
	        status.setStatus(false);
	        status.setMessageIfAny(e.getMessage());
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(status);
	    }
	}

	
	 @Autowired
	    private CategoryServiceRepository categoryServiceRepository;

	    @PostMapping("/categories")
	    public void addCategory(@RequestBody Category category) {
	        categoryServiceRepository.addCategory(category);
	    }

}



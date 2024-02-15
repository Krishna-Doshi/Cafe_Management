package com.cdac.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.Admin;
import com.cdac.exception.AdminServiceException;
import com.cdac.repository.AdminRepository;

@Service
public class AdminService {
	@Autowired
	public AdminRepository adminRepository;
	
	public int register(Admin admin) {
		Optional<Admin> checkAdmin=adminRepository.findByEmail(admin.getEmail());
//		if(checkAdmin.isEmpty()) {
		Admin savedAdmin=adminRepository.save(admin);
	
		return savedAdmin.getId();
//		}else throw new AdminServiceException("Admin already registered!!");
//	}
	
	
	}

	 public Admin authenticate(String email, String password) throws AdminServiceException {
	        Admin admin = adminRepository.findByEmailAndPassword(email, password);

	        if (admin == null) {
	            throw new AdminServiceException("Invalid username or password");
	        }
	        else {
	        	return admin;
	        }

	        // Check if the provided password matches the hashed password stored in the database
//	        if (passwordEncoder.matches(password, admin.getPassword())) {
//	            return admin;
//	        } else {
//	            throw new AdminServiceException("Invalid username or password");
//	        }
	    }
}

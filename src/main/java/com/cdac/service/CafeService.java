package com.cdac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.Cafe;
import com.cdac.exception.CafeServiceException;
import com.cdac.exception.CustomerServiceException;
import com.cdac.repository.CafeRepository;

import jakarta.transaction.Transactional;



@Service
public class CafeService {
	
	@Autowired
	public CafeRepository cafeRepository;
	
	
	
	
	public void register(Cafe cafe) {
		Optional<Cafe> checkCafe=cafeRepository.findByName(cafe.getName());
//		if(checkCafe.isEmpty()) {
		Cafe savedCafe=cafeRepository.save(cafe);
	}
		
//		}else throw new CafeServiceException("Cafe already Added!!");
//	}
	public Cafe login(String email, String password) throws CafeServiceException {
		Optional<Cafe> cafe = cafeRepository.findByEmailAndPassword(email,password);
		if(cafe.isPresent())
			return cafe.get();
		else
			throw new CustomerServiceException("Invalid Email/Password");
	}

	public List<Cafe> fetchAll() {
        return cafeRepository.findAll();
    }
	@Transactional
    public void deleteCafe( int cafeId) {
        cafeRepository.deleteCafe( cafeId);
    }
}


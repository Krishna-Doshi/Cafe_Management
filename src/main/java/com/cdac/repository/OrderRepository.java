package com.cdac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entity.Cafe;
import com.cdac.entity.Customer;
import com.cdac.entity.Order;
import com.cdac.entity.OrderItem;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer(Customer customer);
    List<Order> findByCafe(Cafe cafe);
	
	public void save(OrderItem orderItem);
}

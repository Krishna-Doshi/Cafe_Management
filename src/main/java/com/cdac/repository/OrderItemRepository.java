package com.cdac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cdac.entity.Cafe;
import com.cdac.entity.Order;
import com.cdac.entity.OrderItem;
import com.cdac.entity.Product;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
//    @Query("SELECT SUM(oi.quantity) FROM OrderItem oi WHERE oi.product = :product")
//    int getQuantityByProduct(@Param("product") Product product);
//    
//    @Query("SELECT oi.order.cafe.cafeName FROM OrderItem oi WHERE oi.product = :product")
//    String getCafeNameByProduct(@Param("cafe") Cafe cafe);

}
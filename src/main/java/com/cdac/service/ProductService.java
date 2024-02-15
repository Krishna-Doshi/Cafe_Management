package com.cdac.service;

import com.cdac.dto.ProductDetailsDto;
import com.cdac.repository.Order1Repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdac.entity.Order;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private Order1Repository order1Repository;

    public List<ProductDetailsDto> getProductDetailsForApprovedOrders() {
    	return order1Repository.getProductDetailsForApprovedOrders();
    }
    @Transactional
    public void updateOrder(Long orderId, Order updatedOrder) {
        // Retrieve the existing order from the database
        Order existingOrder = order1Repository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Update the existing order with the new details
        existingOrder.setStatus(updatedOrder.getStatus());
        existingOrder.setTotalAmount(updatedOrder.getTotalAmount());
        // Update other fields as needed

        // Save the updated order back to the database
        order1Repository.save(existingOrder);
    }

    @Transactional
    public List<ProductDetailsDto> updateOrderItemQuantity(Long orderId, int productId, int quantity) {
        Object log;
		try {
            order1Repository.updateOrderItemQuantity(orderId, productId, quantity);

            // Fetch the updated details
            List<ProductDetailsDto> updatedDetails = order1Repository.getProductDetailsForApprovedOrders();

           
            return updatedDetails;
        } catch (Exception e) {
            
            throw new RuntimeException("Error updating order item quantity", e);
        }
    }

    @Transactional
    public void deleteOrderItem(Long orderId, int productId) {
        order1Repository.deleteOrderItem(orderId, productId);
    }
}


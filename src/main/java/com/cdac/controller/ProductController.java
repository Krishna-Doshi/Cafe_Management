package com.cdac.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.ProductDetailsDto;
import com.cdac.entity.Order;
import com.cdac.service.ProductService;

@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/details/approved")
    public ResponseEntity<List<ProductDetailsDto>> getAllProductDetails() {
        List<ProductDetailsDto> productDetailsList = productService.getProductDetailsForApprovedOrders();
        return new ResponseEntity<>(productDetailsList, HttpStatus.OK);
    }
    @PutMapping("/updateQuantity")
    public ResponseEntity<List<ProductDetailsDto>> updateOrderItemQuantity(@RequestParam Long orderId,
                                                                          @RequestParam int productId,
                                                                          @RequestParam int quantity) {
        try {
            List<ProductDetailsDto> updatedDetails = productService.updateOrderItemQuantity(orderId, productId, quantity);
            return new ResponseEntity<>(updatedDetails, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/deleteOrderItem")
    public ResponseEntity<String> deleteOrderItem(@RequestParam Long orderId, @RequestParam int productId) {
        try {
            productService.deleteOrderItem(orderId, productId);
            return new ResponseEntity<>("Order item deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}




package com.cdac.controller;

import com.cdac.dto.OrderRequestDto;
import com.cdac.entity.Order;
import com.cdac.service.BillService;
import com.cdac.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BillService billService;

    @PostMapping("/placeOrderWithItemsAndGenerateBill")
    public ResponseEntity<String> placeOrderWithItemsAndGenerateBill(@RequestBody OrderRequestDto orderRequest) {
        try {
            Order order = orderRequest.getOrder();
            if (orderRequest.getOrderItems() == null || orderRequest.getOrderItems().isEmpty()) {
                return new ResponseEntity<>("Order items cannot be empty", HttpStatus.BAD_REQUEST);
            }

            orderService.placeOrderWithItemsAndGenerateBill(order, orderRequest.getOrderItems());
            billService.generateBill(order);

            return new ResponseEntity<>("Order placed successfully, and bill generated", HttpStatus.OK);
        } catch (Exception e) {
            
            return new ResponseEntity<>("Failed to place order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

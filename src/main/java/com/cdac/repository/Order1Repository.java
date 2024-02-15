package com.cdac.repository;

import com.cdac.entity.Order;
import com.cdac.entity.OrderItem;
import com.cdac.dto.ProductDetailsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Order1Repository extends JpaRepository<Order, Long> {

	@Query("SELECT new com.cdac.dto.ProductDetailsDto(p.name, c.name, o.totalAmount, oi.quantity, ca.name) " +
	        "FROM Order o " +
	        "JOIN o.orderItems oi " +
	        "JOIN oi.product p " +
	        "JOIN p.category c " +
	        "JOIN o.cafe ca")
	List<ProductDetailsDto> getProductDetailsForApprovedOrders();

    
    @Modifying
    @Query("UPDATE OrderItem oi " +
            "SET oi.quantity = :quantity " +
            "WHERE oi.order.orderId = :orderId AND oi.product.productId = :productId")
    void updateOrderItemQuantity(@Param("orderId") Long orderId,
                                 @Param("productId") int productId,
                                 @Param("quantity") int quantity);

    Optional<Order> findByOrderItemsProductProductIdAndOrderId(int productId, Long orderId);
    
    @Modifying
    @Query("DELETE FROM OrderItem oi WHERE oi.order.orderId = :orderId AND oi.product.productId = :productId")
    void deleteOrderItem(@Param("orderId") Long orderId, @Param("productId") int productId);

}

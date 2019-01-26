package com.invillia.acme.repository;

import com.invillia.acme.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Murilo Marazzi on 25/01/2019.
 */

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

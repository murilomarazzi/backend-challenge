package com.invillia.acme.repository;

import com.invillia.acme.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Murilo Marazzi on 25/01/2019.
 */

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
}

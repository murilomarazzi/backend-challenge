package com.invillia.acme.repository;

import com.invillia.acme.domain.Orderr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Murilo Marazzi on 25/01/2019.
 */

@Repository
public interface OrderRepository extends JpaRepository<Orderr,Long> {

}

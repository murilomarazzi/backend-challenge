package com.invillia.acme.repository;

import com.invillia.acme.domain.Orderr;
import com.invillia.acme.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import util.enums.OrderStatusEnum;

/**
 * Created by Murilo Marazzi on 25/01/2019.
 */

@Repository
public interface OrderRepository extends JpaRepository<Orderr,Long>, PagingAndSortingRepository<Orderr,Long> {

    Page<Orderr> findAllByStoreAndStatus(Store store, OrderStatusEnum status, Pageable page);

}

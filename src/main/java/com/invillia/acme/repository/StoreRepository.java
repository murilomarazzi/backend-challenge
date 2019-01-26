package com.invillia.acme.repository;

import com.invillia.acme.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Murilo Marazzi on 25/01/2019.
 */

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>, PagingAndSortingRepository<Store, Long> {

    Page<Store> findByNameContainingAllIgnoringCase(String storeName, Pageable page);

    Page<Store> findByAddressContainingAllIgnoringCase(String storeAddress, Pageable page);
}

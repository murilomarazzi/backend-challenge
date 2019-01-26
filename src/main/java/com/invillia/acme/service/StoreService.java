package com.invillia.acme.service;

import com.invillia.acme.domain.Store;
import com.invillia.acme.repository.StoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Murilo Marazzi on 26/01/2019.
 */

@Service
public class StoreService implements IStoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public List<Store> getListStoreByName(String name, int page, int size, String sortDir, String sort) {

        PageRequest pageReq
                = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);

        Page<Store> stores = storeRepository.findByNameContainingAllIgnoringCase(name, pageReq);
        return stores.getContent();
    }

    @Override
    public List<Store> getListStoreByAddress(String address, int page, int size, String sortDir, String sort) {
        PageRequest pageReq
                = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);

        Page<Store> stores = storeRepository.findByAddressContainingAllIgnoringCase(address, pageReq);
        return stores.getContent();
    }

    @Override
    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public void updateStore(Store store) {
        storeRepository.save(store);
    }

}

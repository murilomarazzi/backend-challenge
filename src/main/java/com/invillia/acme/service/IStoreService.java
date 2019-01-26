package com.invillia.acme.service;

import com.invillia.acme.domain.Store;

import java.util.List;

/**
 * Created by Murilo Marazzi on 26/01/2019.
 */
public interface IStoreService {

    List<Store> getListStoreByName(String name, int page, int size, String sortDir, String sort);

    Store createStore(Store store);

    void updateStore(Store store);
}

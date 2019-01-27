package com.invillia.acme.service;

import com.invillia.acme.domain.Store;
import com.invillia.acme.repository.StoreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Murilo Marazzi on 26/01/2019.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreRepositoryTest {

    @Autowired
    StoreRepository repository;

    @Test
    public void findStoreDonaBenta() {

        PageRequest pageReq
                = PageRequest.of(0, 1, Sort.Direction.fromString("asc"), "id");

        Page<Store> storeNotFound = this.repository.findByNameContainingAllIgnoringCase("123", pageReq);

        assertThat(storeNotFound.getTotalElements()).isEqualTo(0);

        Page<Store> store = this.repository.findByNameContainingAllIgnoringCase("Dona Benta Fast Food", pageReq);
        assertThat(store).isNotNull();
        assertThat(store.getContent()).isNotEmpty();
        assertThat(store.getContent().get(0).getName()).isEqualTo("Dona Benta Fast Food");
    }

    @Test
    public void findAddressRuaDasComidas() {

        PageRequest pageReq
                = PageRequest.of(0, 1, Sort.Direction.fromString("asc"), "id");

        Page<Store> storeNotFound = this.repository.findByAddressContainingAllIgnoringCase("xxxx", pageReq);

        assertThat(storeNotFound.getTotalElements()).isEqualTo(0);

        Page<Store> stores = this.repository.findByAddressContainingAllIgnoringCase("Rua das Comidas", pageReq);
        assertThat(stores).isNotNull();
        assertThat(stores.getContent()).isNotEmpty();
        assertThat(stores.getContent().get(0).getAddress()).isEqualTo("Rua das Comidas");
    }


}

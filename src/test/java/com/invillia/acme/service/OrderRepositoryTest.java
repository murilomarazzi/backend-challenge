package com.invillia.acme.service;

import com.invillia.acme.domain.Orderr;
import com.invillia.acme.domain.Store;
import com.invillia.acme.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import util.enums.OrderStatusEnum;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Murilo Marazzi on 26/01/2019.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    OrderRepository repository;

    @Test
    public void findOrderByStoreAndStatus() {

        PageRequest pageReq
                = PageRequest.of(0, 1, Sort.Direction.fromString("asc"), "id");

        Store store = new Store("My Store", "My Address");
        store.setId(0L);
        Page<Orderr> orderrNotFound = this.repository.findAllByStoreAndStatus(store, OrderStatusEnum.C, pageReq);

        assertThat(orderrNotFound.getTotalElements()).isEqualTo(0);

        store = new Store("Dona Benta Fast Food", "Rua das Comidas");
        store.setId(1L);
        Page<Orderr> orders = this.repository.findAllByStoreAndStatus(store, OrderStatusEnum.R, pageReq);
        assertThat(orders).isNotNull();
        assertThat(orders.getContent()).isNotEmpty();
        assertThat(orders.getContent().get(0).getStore().getName()).isEqualTo("Dona Benta Fast Food");
    }

}

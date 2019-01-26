package com.invillia.acme.carga;

import com.invillia.acme.domain.OrderItem;
import com.invillia.acme.domain.Orderr;
import com.invillia.acme.domain.Payment;
import com.invillia.acme.domain.Store;
import com.invillia.acme.repository.StoreRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import util.enums.OrderStatusEnum;
import util.enums.PaymentStatusEnum;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Murilo Marazzi on 25/01/2019.
 */

@Component
public class RepositoryTest implements ApplicationRunner {

    private final StoreRepository repository;

    public RepositoryTest(final StoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Store store = new Store("Dona Benta Fast Food", "Rua das Comidas");

        OrderItem item1 = new OrderItem("Dogao tradicional picante", new BigDecimal("8"), 1);
        OrderItem item2 = new OrderItem("Dogao tradicional salada", new BigDecimal("5"), 2);

        List<OrderItem> itens = new ArrayList<>();
        itens.add(item1);
        itens.add(item2);

        Payment payment1 = new Payment("4024007138518286", Date.from(Instant.now()), PaymentStatusEnum.E);
        List<Payment> payments = new ArrayList<>();
        payments.add(payment1);

        Orderr order = new Orderr(payments, itens, Date.from(Instant.now()), "Rua dos Famintos", OrderStatusEnum.R);

        List<Orderr> orderrs = new ArrayList<>();
        orderrs.add(order);

        payment1.setOrderr(order);
        order.setStore(store);
        store.setOrderrs(orderrs);
        repository.saveAndFlush(store);

        store = new Store("House Burguer Fast Food", "Rua dos Hamburguers");
        repository.saveAndFlush(store);
    }
}

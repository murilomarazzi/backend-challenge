package com.invillia.acme.service;

import com.invillia.acme.domain.Orderr;
import com.invillia.acme.domain.Store;
import com.invillia.acme.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import util.enums.OrderStatusEnum;

import java.util.List;

/**
 * Created by Murilo Marazzi on 26/01/2019.
 */

@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Orderr> getListOrderByStoreAndStatus(Store store, OrderStatusEnum status,
                                           int page, int size, String sortDir, String sort) {
        PageRequest pageReq
                = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);

        Page<Orderr> order = orderRepository.findAllByStoreAndStatus(store, status, pageReq);

        return order.getContent();
    }

    @Override
    public Orderr createOrder(Orderr orderr) {
        return null;
    }

    @Override
    public void refundOrder(Orderr orderr) {

    }
}

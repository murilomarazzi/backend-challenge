package com.invillia.acme.service;

import com.invillia.acme.domain.Orderr;
import com.invillia.acme.domain.Store;
import util.enums.OrderStatusEnum;

import java.util.List;

/**
 * Created by Murilo Marazzi on 26/01/2019.
 */
public interface IOrderService {

    List<Orderr> getListOrderByStoreAndStatus(Store store, OrderStatusEnum status,
                                             int page, int size, String sortDir, String sort);

    Orderr createOrder(Orderr orderr);

    void refundOrder(Orderr orderr);
}

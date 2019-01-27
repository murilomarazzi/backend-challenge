package com.invillia.acme.rest;

import com.invillia.acme.domain.Orderr;
import com.invillia.acme.dto.OrderDTO;
import com.invillia.acme.service.IOrderService;
import com.invillia.acme.util.GlobalResponseMessage;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * Created by Murilo Marazzi on 26/01/2019.
 */

@RestController
public class NewOrderController {

    private final ModelMapper modelMapper;
    private final IOrderService orderService;

    public NewOrderController(ModelMapper modelMapper, IOrderService orderService) {
        this.modelMapper = modelMapper;
        this.orderService = orderService;
    }

    @PostMapping("/rest/order/new")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) throws ParseException {
        Orderr order = convertToEntity(orderDTO);
        Orderr orderCreated = orderService.createOrder(order);
        return new ResponseEntity<>(convertToDto(orderCreated), HttpStatus.CREATED);
    }

    private Orderr convertToEntity(OrderDTO orderDTO) throws ParseException {
        return modelMapper.map(orderDTO, Orderr.class);
    }

    private OrderDTO convertToDto(Orderr order) {
        OrderDTO dto = modelMapper.map(order, OrderDTO.class);
        dto.setMenssage(new GlobalResponseMessage(GlobalResponseMessage.Type.success,
                HttpStatus.OK.value()));
        return dto;
    }
}

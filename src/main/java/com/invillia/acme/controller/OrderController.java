package com.invillia.acme.controller;

import com.invillia.acme.domain.Orderr;
import com.invillia.acme.dto.OrderDTO;
import com.invillia.acme.exception.NoResultFoundException;
import com.invillia.acme.repository.OrderRepository;
import com.invillia.acme.service.IOrderService;
import com.invillia.acme.util.GlobalResponseMessage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Murilo Marazzi on 26/01/2019.
 */

@RestController
@RequestMapping("/order")
public class OrderController {

    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;

    public OrderController(ModelMapper modelMapper, OrderRepository orderRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
    }

    @ApiOperation(value = "Find an order by id", response = OrderDTO.class)
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Return an Order and the success message",
                    response = OrderDTO.class
            ),
            @ApiResponse(
                    code = 404,
                    message = "Return a unsuccess message",
                    response = OrderDTO.class
            )

    })
    @GetMapping("/id/{orderId}")
    public ResponseEntity<OrderDTO> findByOrderId(@PathVariable("orderId") Long orderId) {

        OrderDTO orderDTO;

        try {
            orderDTO = convertToDto(orderRepository.findById(orderId).orElse(null));

        } catch (NoResultFoundException ex) {
            orderDTO = builderException(ex.getMessage());
        }
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    private OrderDTO convertToDto(Orderr order) {
        OrderDTO dto = modelMapper.map(order, OrderDTO.class);
        dto.setMenssage(new GlobalResponseMessage(GlobalResponseMessage.Type.success,
                HttpStatus.OK.value()));
        return dto;
    }

    private Orderr convertToEntity(OrderDTO orderDTO) throws ParseException {
        return modelMapper.map(orderDTO, Orderr.class);
    }

    private List<OrderDTO> mapToList(List<Orderr> orders) {
        return orders.stream()
                .map(order -> convertToDto(order))
                .collect(Collectors.toList());
    }

    private OrderDTO builderException(String message) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setMenssage(new GlobalResponseMessage(GlobalResponseMessage.Type.info,
                HttpStatus.NOT_FOUND.value(), message));

        return orderDTO;
    }
}

package com.invillia.acme.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.invillia.acme.domain.OrderItem;
import com.invillia.acme.domain.Payment;
import com.invillia.acme.domain.Store;
import com.invillia.acme.util.GlobalResponseMessage;
import util.enums.OrderStatusEnum;

import java.util.Date;
import java.util.List;

/**
 * Created by Murilo Marazzi on 26/01/2019.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private Long id;
    private Store store;

    private List<Payment> payments;
    private List<OrderItem> orderItens;
    private Date confirmationDate;
    private String address;
    private OrderStatusEnum status;
    private GlobalResponseMessage menssage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<OrderItem> getOrderItens() {
        return orderItens;
    }

    public void setOrderItens(List<OrderItem> orderItens) {
        this.orderItens = orderItens;
    }

    public Date getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(Date confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public GlobalResponseMessage getMenssage() {
        return menssage;
    }

    public void setMenssage(GlobalResponseMessage menssage) {
        this.menssage = menssage;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((menssage == null) ? 0 : menssage.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderDTO other = (OrderDTO) obj;

        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}

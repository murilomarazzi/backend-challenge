package com.invillia.acme.domain;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import util.enums.OrderStatusEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by Murilo Marazzi on 25/01/2019.
 */

@Entity
public class Orderr {

    public Orderr(List<Payment> payments, List<OrderItem> orderItens,
                  Date confirmationDate, String address, OrderStatusEnum status) {

        this.payments = payments;
        this.orderItens = orderItens;
        this.confirmationDate = confirmationDate;
        this.address = address;
        this.status = status;
    }

    public Orderr() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true)
    private Store store;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderr", fetch = FetchType.LAZY)
    private List<Payment> payments;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItens;

    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    private Date confirmationDate;

    @NotNull
    @Length(min = 2, max = 300)
    private String address;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Orderr other = (Orderr) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}

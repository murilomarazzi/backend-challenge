package com.invillia.acme.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.invillia.acme.util.GlobalResponseMessage;

/**
 * Created by Murilo Marazzi on 25/01/2019.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoreDTO {

    private Long id;
    private String name;
    private String address;
    private GlobalResponseMessage menssage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        result = prime * result + ( (menssage == null) ? 0 : menssage.hashCode());
        result = prime * result + ( (id == null) ? 0 : id.hashCode());
        result = prime * result + ( (name == null) ? 0 : name.hashCode());
        result = prime * result + ( (address == null) ? 0 : address.hashCode());
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
        StoreDTO other = (StoreDTO) obj;

        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}

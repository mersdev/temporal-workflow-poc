package com.xdman.temporalpoc.domain;

import com.xdman.temporalpoc.domain.AbstractPersistable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "ID_SEQUENCE", sequenceName = "ITEM_SEQUENCE_NAME")
public class Items extends AbstractPersistable<Items> {
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "unitPrice")
    private Double unitPrice;

    public Items(String itemName, Integer quantity, Double unitPrice) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
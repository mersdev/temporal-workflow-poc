package com.xdman.temporalpoc.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xdman.temporalpoc.domain.AbstractPersistable;
import com.xdman.temporalpoc.domain.Items;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchase_order")
@SequenceGenerator(name = "ID_SEQUENCE", sequenceName = "ORDER_ID_SEQ", allocationSize = 1)
public class Order extends AbstractPersistable<Order> {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(
            name = "order_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "item_order_id_fkey")
    )
    private List<Items> items = new ArrayList<>();

    private String email;
    private String platForm;
    @Enumerated
    private OrderStatus status;

    public Order(List<Items> itemList, String email, String platForm, OrderStatus status) {
        this.items = itemList;
        this.email = email;
        this.platForm = platForm;
        this.status = status;
    }

    public Order() {}

    public List<Items> getItemList() {
        return items;
    }

    public void setItemList(List<Items> itemList) {
        this.items = itemList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlatForm() {
        return platForm;
    }

    public void setPlatForm(String platForm) {
        this.platForm = platForm;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}

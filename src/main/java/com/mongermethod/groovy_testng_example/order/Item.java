package com.mongermethod.groovy_testng_example.order;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "monger_item")
@SequenceGenerator(name = "itemSequence", sequenceName = "seq_item", allocationSize = 1)
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemSequence")
    private long id;
    @Column(name = "order_id")
    private long orderId;
    private String sku;
    @Column(name = "purchase_price")
    private BigInteger purchasePrice;
    private int quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigInteger getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigInteger purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

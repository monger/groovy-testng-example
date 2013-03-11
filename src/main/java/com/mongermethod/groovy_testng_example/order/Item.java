package com.mongermethod.groovy_testng_example.order;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "monger_item")
@SequenceGenerator(name = "itemSequence", sequenceName = "seq_item", allocationSize = 1)
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemSequence")
    private long id;

    @Column(name = "order_id", nullable = false)
    private long orderId;

    private String sku;

    @Column(name = "purchase_price")
    private BigDecimal purchasePrice;
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

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != item.id) return false;
        if (orderId != item.orderId) return false;
        if (quantity != item.quantity) return false;
        if (!purchasePrice.equals(item.purchasePrice)) return false;
        if (!sku.equals(item.sku)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + sku.hashCode();
        result = 31 * result + purchasePrice.hashCode();
        result = 31 * result + quantity;
        return result;
    }
}

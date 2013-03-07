package com.mongermethod.groovy_testng_example.order;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "monger_order")
@SequenceGenerator(name = "orderSequence", sequenceName = "seq_order", allocationSize = 1)
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderSequence")
    private long id;
    private String username;
    private Date date;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Set<Item> orderItems;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Item> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<Item> orderItems) {
        this.orderItems = orderItems;
    }
}

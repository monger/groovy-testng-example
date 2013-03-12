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

    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(mappedBy = "orderId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (date.getTime() != order.date.getTime()) return false;
        if (!username.equals(order.username)) return false;
        if (orderItems != null) {
            if (orderItems.size() != order.orderItems.size()) return false;
            for (Item item : orderItems) {
                boolean match = false;
                for (Item oItem : order.getOrderItems()) {
                    if (item.getId() == oItem.getId() &&
                            item.getOrderId() == oItem.getOrderId() &&
                            item.getSku().equals(oItem.getSku()) &&
                            item.getPurchasePrice().equals(oItem.getPurchasePrice()) &&
                            item.getQuantity() == oItem.getQuantity()) {
                        match = true;
                    }
                }
                if (!match) return false;
            }
        } else if(order.orderItems != null) {
            return false;
        }

        return true;
    }
}

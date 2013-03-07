package com.mongermethod.groovy_testng_example.order;

import java.util.List;

public interface OrderDao {
    void saveOrder(Order order);
    Order loadOrder(long orderId);
    void deleteOrder(Order order);
    List<Item> getOrderItemsForOrder(long orderId);
    Item getOrderItem(long orderItemId);
    void deleteOrderItem(Item orderItem);
}

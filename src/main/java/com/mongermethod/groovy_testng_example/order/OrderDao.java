package com.mongermethod.groovy_testng_example.order;

import java.util.List;

public interface OrderDao {
    void saveOrder(Order order);
    Order loadOrder(long orderId);
    void deleteOrder(Order order);
    void saveItem(Item orderItem);
    List<Item> getOrderItemsForOrder(long orderId);
    Item loadOrderItem(long orderItemId);
    void deleteOrderItem(Item orderItem);
}

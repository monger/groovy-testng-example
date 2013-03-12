package com.mongermethod.groovy_testng_example.order

import com.mongermethod.groovy_testng_example.UnitBootstrap
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import javax.annotation.Resource


class OrderVOTest extends UnitBootstrap {
    @Resource(name =  "newOrder2")
    Order testOrder
    @Resource(name = "newOrderItemList2")
    List<Item> testOrderItems

    @BeforeMethod(groups = ["unit"])
    void setup() {
        def orderId = 25

        testOrderItems.eachWithIndex { item, index ->
            item.id = index + 1
            item.orderId = orderId
        }
        testOrder.id = orderId
        testOrder.orderItems = testOrderItems
    }

    @Test(groups = "unit")
    void "test the proper implementation of accessors and mutators"() {
        Order order = new Order(
                id: testOrder.id,
                username: testOrder.username,
                date: testOrder.date,
                orderItems: testOrderItems
        )

        assert order == testOrder
    }
}

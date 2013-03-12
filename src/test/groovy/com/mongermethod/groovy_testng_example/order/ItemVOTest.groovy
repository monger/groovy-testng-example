package com.mongermethod.groovy_testng_example.order
import com.mongermethod.groovy_testng_example.UnitBootstrap
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import javax.annotation.Resource

class ItemVOTest extends UnitBootstrap {
    @Resource(name = "newOrderItem1")
    Item testItem

    @BeforeMethod
    void setup() {
        testItem.id = 5
        testItem.orderId = 10
    }

    @Test(groups = "unit")
    void "test the proper implementation of accessors and mutators"() {
        def item = new Item(
                id: testItem.id,
                orderId: testItem.orderId,
                sku: testItem.sku,
                purchasePrice: testItem.purchasePrice,
                quantity: testItem.quantity
        )
        assert item == testItem
    }
}

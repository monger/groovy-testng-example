package com.mongermethod.groovy_testng_example.order

import com.mongermethod.groovy_testng_example.IntegrationBootstrap
import org.hibernate.SessionFactory
import org.springframework.test.context.transaction.TransactionConfiguration
import org.springframework.transaction.annotation.Transactional
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

import javax.annotation.Resource

@Transactional
@TransactionConfiguration(defaultRollback = true)
class OrderIT extends IntegrationBootstrap {
    @Resource
    OrderDao orderDao;
    @Resource
    private SessionFactory sessionFactory;

    @Resource
    List<Order> newOrderList
    @Resource
    Set<Item> newOrderItemList1
    @Resource
    Set<Item> newOrderItemList2
    @Resource
    Set<Item> newOrderItemList3

    @BeforeClass(groups = ["long-integration"])
    void "create order and order line item records"() {
        newOrderList.eachWithIndex { order, index ->
            orderDao.saveOrder(order)
            order.orderItems = new HashSet<Item>()
            switch (index) {
                case 0:
                    newOrderItemList1.collect {
                        it.orderId = order.id
                        orderDao.saveItem(it)
                    }
                    order.orderItems = newOrderItemList1
                    break;
                case 1:
                    newOrderItemList2.collect{
                        it.orderId = order.id
                        orderDao.saveItem(it)
                    }
                    order.orderItems = newOrderItemList2
                    break
                case 2:
                    newOrderItemList3.collect{
                        it.orderId = order.id
                        orderDao.saveItem(it)
                    }
                    order.orderItems = newOrderItemList3
            }
        }
    }

    @Test(groups = ["long-integration"])
    void "test retrieving an order"() {
        def loadedOrder = orderDao.loadOrder(newOrderList[0].id)
        assert loadedOrder == newOrderList[0]
    }

    @Test(groups = ["long-integration"])
    void "test retrieving an order item"() {
        def loadedOrderItem = orderDao.loadOrderItem(((Item) newOrderList[0].orderItems.toArray()[0]).id)
        assert loadedOrderItem == newOrderList[0].orderItems.toArray()[0]
    }

    @Test(groups = ["long-integration"])
    void "test deletion of an order item"() {
        def orderItemId = ((Item) newOrderList[1].orderItems.toArray()[0]).id
        def deletableOrderItem = orderDao.loadOrderItem(orderItemId)
        orderDao.deleteOrderItem(deletableOrderItem)
        def loadedOrderItem = orderDao.loadOrderItem(orderItemId)
        assert loadedOrderItem == null
    }

    @Test(groups = ["long-integration"])
    void "test deletion of an order"() {
        orderDao.deleteOrder(newOrderList[2])
        def loadedOrder = orderDao.loadOrder(newOrderList[2].id)
        assert loadedOrder == null
    }
}

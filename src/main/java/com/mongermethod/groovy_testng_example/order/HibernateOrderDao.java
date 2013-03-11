package com.mongermethod.groovy_testng_example.order;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Repository
public class HibernateOrderDao implements OrderDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void saveOrder(Order order) {
        sessionFactory.getCurrentSession().saveOrUpdate(order);
    }

    @Override
    public Order loadOrder(long orderId) {
        return (Order) sessionFactory.getCurrentSession()
                .createCriteria(Order.class)
                .add(Restrictions.eq("id", orderId))
                .uniqueResult();
    }

    @Override
    public void deleteOrder(Order order) {
        sessionFactory.getCurrentSession().delete(order);
    }

    @Override
    public void saveItem(Item orderItem) {
        sessionFactory.getCurrentSession().saveOrUpdate(orderItem);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Item> getOrderItemsForOrder(long orderId) {
        return (List<Item>) sessionFactory.getCurrentSession()
                .createCriteria(Item.class)
                .add(Restrictions.eq("orderId", orderId))
                .list();
    }

    @Override
    public Item loadOrderItem(long orderItemId) {
        return (Item) sessionFactory.getCurrentSession()
                .createCriteria(Item.class)
                .add(Restrictions.eq("id", orderItemId))
                .uniqueResult();
    }

    @Override
    public void deleteOrderItem(Item orderItem) {
        sessionFactory.getCurrentSession().delete(orderItem);
    }
}

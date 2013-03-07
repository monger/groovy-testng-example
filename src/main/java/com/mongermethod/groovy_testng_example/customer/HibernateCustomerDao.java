package com.mongermethod.groovy_testng_example.customer;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Repository
public class HibernateCustomerDao implements CustomerDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Customer> getAllCustomers() {
        return (List<Customer>) sessionFactory.getCurrentSession()
                .createCriteria(Customer.class)
                .list();
    }

    @Override
    public void saveCustomer(Customer customer) {
        sessionFactory.getCurrentSession().saveOrUpdate(customer);
    }

    @Override
    public Customer loadCustomer(String username) {
        return (Customer) sessionFactory.getCurrentSession()
                .createCriteria(Customer.class)
                .add(Restrictions.eq("username", username))
                .uniqueResult();
    }

    @Override
    public void deleteCustomer(Customer customer) {
        sessionFactory.getCurrentSession().delete(customer);
    }
}

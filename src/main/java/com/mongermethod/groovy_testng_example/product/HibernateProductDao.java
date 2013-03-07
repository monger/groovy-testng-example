package com.mongermethod.groovy_testng_example.product;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Repository
public class HibernateProductDao implements ProductDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void saveProduct(Product product) {
        sessionFactory.getCurrentSession().saveOrUpdate(product);
    }

    @Override
    public Product loadProduct(String sku) {
        return (Product) sessionFactory.getCurrentSession()
                .createCriteria(Product.class)
                .add(Restrictions.eq("sku", sku))
                .uniqueResult();
    }

    @Override
    public void deleteProduct(Product product) {
        sessionFactory.getCurrentSession().delete(product);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getAllProducts() {
        return (List<Product>) sessionFactory.getCurrentSession()
                .createCriteria(Product.class)
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getProductSubset(String[] skus) {
        return (List<Product>) sessionFactory.getCurrentSession()
                .createCriteria(Product.class)
                .add(Restrictions.in("sku", skus))
                .list();
    }
}

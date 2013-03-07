package com.mongermethod.groovy_testng_example.customer

import com.mongermethod.groovy_testng_example.IntegrationBootstrap
import org.springframework.test.context.transaction.TransactionConfiguration
import org.springframework.transaction.annotation.Transactional
import org.testng.annotations.Test

import javax.annotation.Resource

@TransactionConfiguration(defaultRollback = true)
@Transactional
class CustomerIT extends IntegrationBootstrap {
    @Resource
    CustomerDao customerDao
    @Resource
    Customer newCustomer1
    @Resource
    Customer newCustomer2
    @Resource
    Customer newCustomer3
    @Resource
    List<Customer> newUserList

    @Test(groups = "integration")
    void "ensure that there are customers already in the table"() {
        def customers = customerDao.allCustomers

        assert customers.size() == 3
    }

    @Test(groups = "integration")
    void "ensure that the customer gets saved"() {
        def customer = new Customer(
                username: newCustomer1.username,
                firstName: newCustomer1.firstName,
                lastName: newCustomer1.lastName
        )

        customerDao.saveCustomer(customer)
        def savedCustomer = customerDao.loadCustomer(newCustomer1.username)

        assert savedCustomer != null
        assert savedCustomer.firstName == newCustomer1.firstName
    }

    @Test(groups = "integration")
    void "ensure that the customer gets deleted"() {
        def customer = new Customer(
                username: newCustomer2.username,
                firstName: newCustomer2.firstName,
                lastName: newCustomer2.lastName
        )

        customerDao.saveCustomer(customer)
        def savedCustomer = customerDao.loadCustomer(newCustomer2.username)
        assert savedCustomer != null

        customerDao.deleteCustomer(savedCustomer)
        savedCustomer = customerDao.loadCustomer(newCustomer2.username)
        assert savedCustomer == null
    }
}

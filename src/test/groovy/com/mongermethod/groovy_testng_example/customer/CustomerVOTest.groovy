package com.mongermethod.groovy_testng_example.customer

import com.mongermethod.groovy_testng_example.UnitBootstrap
import org.testng.annotations.Test

import javax.annotation.Resource

class CustomerVOTest extends UnitBootstrap {
    @Resource(name = "newCustomer2")
    Customer fullCustomer

    @Test(groups = "unit")
    void "test the proper implementation of accessors and mutators"() {
        def customer = new Customer(
                username: fullCustomer.username,
                firstName: fullCustomer.firstName,
                lastName: fullCustomer.lastName,
                email: fullCustomer.email
        )

        assert customer == fullCustomer
    }
}

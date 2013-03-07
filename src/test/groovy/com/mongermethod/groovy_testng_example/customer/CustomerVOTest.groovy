package com.mongermethod.groovy_testng_example.customer

import org.testng.annotations.Test

class CustomerVOTest {

    private USERNAME = "godzilla"
    private FIRST_NAME = "Bob"
    private LAST_NAME = "Jones"
    private EMAIL = "bob_jones@nowhere.us.gov"

    @Test(groups = "unit")
    void "test the proper implementation of accessors and mutators"() {
        def customer = new Customer(
                username: USERNAME,
                firstName: FIRST_NAME,
                lastName: LAST_NAME,
                email: EMAIL
        )

        assert customer.username == USERNAME
        assert customer.firstName == FIRST_NAME
        assert customer.lastName == LAST_NAME
        assert customer.email == EMAIL
    }

}

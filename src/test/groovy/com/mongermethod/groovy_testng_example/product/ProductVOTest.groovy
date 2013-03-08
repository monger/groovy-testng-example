package com.mongermethod.groovy_testng_example.product

import com.mongermethod.groovy_testng_example.UnitBootstrap
import org.testng.annotations.Test

import javax.annotation.Resource


class ProductVOTest extends UnitBootstrap {
    @Resource
    Product newProduct1

    @Test(groups = "unit")
    void "test the proper implementation of accessors and mutators"() {
        Product testProduct = new Product(
                sku: newProduct1.sku,
                name: newProduct1.name,
                price: newProduct1.price,
                description: newProduct1.description
        )
        assert testProduct == newProduct1
    }
}

package com.mongermethod.groovy_testng_example.product
import com.mongermethod.groovy_testng_example.IntegrationBootstrap
import org.springframework.test.context.transaction.TransactionConfiguration
import org.springframework.transaction.annotation.Transactional
import org.testng.annotations.Test

import javax.annotation.Resource

@Transactional
@TransactionConfiguration(defaultRollback = true)
class ProductIT extends IntegrationBootstrap {
    @Resource
    ProductDao productDao
    @Resource
    List<Product> newProductList
    @Resource
    Product newProduct1
    @Resource
    Product newProduct2
    @Resource
    Product newProduct3

    @Test(groups = ["integration"])
    void "ensure there are products already in the table"() {
        def expectedListSize = 4
        List<Product> products = productDao.allProducts

        assert products.size() == expectedListSize
    }

    @Test(groups = ["integration"])
    void "test whether a new product gets persisted"() {
        productDao.saveProduct(newProduct1)
        def savedProduct = productDao.loadProduct(newProduct1.sku)

        assert savedProduct == newProduct1
    }

    @Test(groups = ["integration"], dependsOnMethods = ["ensure there are products already in the table", "test whether a new product gets persisted"])
    void "test whether a product actually gets deleted"() {
        productDao.saveProduct(newProduct2)
        def savedProduct = productDao.loadProduct(newProduct2.sku)
        assert savedProduct != null

        productDao.deleteProduct(savedProduct)
        def deletedProduct = productDao.loadProduct(newProduct2.sku)
        assert deletedProduct == null
    }

    @Test(groups = ["integration"], dependsOnMethods = ["ensure there are products already in the table", "test whether a new product gets persisted"])
    void "ensure that product subsets are loading properly"() {
        def productSubset = productDao.allProducts[1..2]
        String[] skus = productSubset.collect{ it.sku }
        def loadedProducts = productDao.getProductSubset(skus)

        assert productSubset == loadedProducts
    }
}

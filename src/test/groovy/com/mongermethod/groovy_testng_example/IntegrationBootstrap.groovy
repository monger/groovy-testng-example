package com.mongermethod.groovy_testng_example
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests
import org.testng.annotations.BeforeGroups

import javax.annotation.Resource

@ContextConfiguration(locations = ["/spring/spring-config.xml", "/spring/spring-integration.xml"])
class IntegrationBootstrap extends AbstractTestNGSpringContextTests {
    @Resource
    HsqlDatabase hsqlDatabase

    @BeforeGroups(groups = "integration")
    void setupDatabase() {
        hsqlDatabase.setUp()
    }
}

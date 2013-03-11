package com.mongermethod.groovy_testng_example
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests
import org.testng.annotations.BeforeClass

import javax.annotation.Resource

@ContextConfiguration(locations = ["/spring/spring-config.xml", "/spring/spring-integration.xml"])
class IntegrationBootstrap extends AbstractTestNGSpringContextTests {
    @Resource
    HsqlDatabase hsqlDatabase

    @Override
    @BeforeClass(groups = ["integration", "long-integration"])
    protected void springTestContextPrepareTestInstance() throws Exception {
        super.springTestContextPrepareTestInstance()
    }

    @BeforeClass(groups = ["integration", "long-integration"], dependsOnMethods = "springTestContextPrepareTestInstance")
    void setupDatabase() {
        hsqlDatabase.setUp()
    }

}

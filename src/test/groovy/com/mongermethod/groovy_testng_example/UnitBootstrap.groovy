package com.mongermethod.groovy_testng_example

import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests

@ContextConfiguration(locations = ["/spring/spring-stub-data.xml"])
class UnitBootstrap extends AbstractTestNGSpringContextTests {

}

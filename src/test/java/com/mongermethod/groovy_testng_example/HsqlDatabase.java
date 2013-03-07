package com.mongermethod.groovy_testng_example;

import liquibase.Liquibase;
import liquibase.database.jvm.HsqlConnection;
import liquibase.logging.LogFactory;
import liquibase.resource.FileSystemResourceAccessor;
import liquibase.resource.ResourceAccessor;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HsqlDatabase {
    private static final String CHANGE_LOG = "src/main/resources/liquibase/master.xml";
    private static final String SHUTDOWN_STOPPER = ";shutdown=false";

    @Value("${url}")
    private String url;
    @Value("${username}")
    private String username;

    private Connection holdingConnection;
    private Liquibase liquibase;

    public void setUp() {
        try {
            ResourceAccessor resourceAccessor = new FileSystemResourceAccessor();
            Class.forName("org.hsqldb.jdbcDriver");

            holdingConnection = getConnectionImpl();
            HsqlConnection hsconn = new HsqlConnection(holdingConnection);
            LogFactory.getLogger().setLogLevel("severe");
            liquibase = new Liquibase(CHANGE_LOG, resourceAccessor, hsconn);
            liquibase.dropAll();
            liquibase.update("test");
            hsconn.close();
        } catch (Exception ex) {
            throw new RuntimeException("Error during database initialization", ex);
        }
    }

    private Connection getConnectionImpl() throws SQLException {
        return DriverManager.getConnection(url + SHUTDOWN_STOPPER, username, null);
    }

}

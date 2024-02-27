package br.com.fiaplanchesclient.bdd;

import javax.sql.DataSource;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MongoDBContainer;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringBootTestLoader {
    static MongoDBContainer mongoDBContainer;

    /**
     * Initial database setup
     */
    @BeforeAll
    public static void setup() {
        System.out.println("starting DB");
        mongoDBContainer = new MongoDBContainer("mongo:latest")
                .withExposedPorts(27017);
        mongoDBContainer.start();
        var mappedPort = mongoDBContainer.getMappedPort(27017);
        System.setProperty("mongodb.container.port", String.valueOf(mappedPort));
        System.out.println(mongoDBContainer.getConnectionString() + "Port:" + mappedPort );
    }

    /**
     * Datasource dynamic configuration
     *
     */
    @TestConfiguration
    static class PostgresTestConfiguration {
        @Bean
        DataSource dataSource() {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.(mongoDBContainer.getJdbcUrl());
            hikariConfig.setUsername(mongoDBContainer.getUsername());
            hikariConfig.setPassword(mongoDBContainer.getPassword());
            return new HikariDataSource(hikariConfig);
//        }
    }

    /**
     * Shutdown
     */
    @AfterAll
    public static void tearDown() {
        System.out.println("closing DB connection");
        mongoDBContainer.stop();
    }
}
package br.com.fiaplanchesclient.bdd;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;

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
//    @TestConfiguration
    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.host", mongoDBContainer::getHost);
        registry.add("spring.data.mongodb.port", mongoDBContainer::getFirstMappedPort);
        registry.add("spring.data.mongodb.database", () -> "fiap-lanches-client");
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
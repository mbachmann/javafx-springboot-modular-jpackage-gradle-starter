
package com.example.javafx.service;

import com.example.javafx.JavaFxSpringApp;
import com.example.javafx.JavaFxSpringAppTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureTestDatabase
@SpringBootTest(classes= JavaFxSpringApp.class)
public class TestServiceTest {

    @Autowired
    private TestService testService;

    @Test
    void contextLoads() {
    }

}


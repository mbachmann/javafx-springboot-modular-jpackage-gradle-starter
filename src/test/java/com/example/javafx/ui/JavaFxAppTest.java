package com.example.javafx.ui;

import com.example.javafx.JavaFxSpringApp;
import com.example.javafx.JavaFxSpringAppTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes= JavaFxSpringApp.class)
public class JavaFxAppTest {

    JavaFxApp javaFxApp = new JavaFxApp();;


    @Test
    void contextLoads() {

    }

}

package com.example.javafx.ui;

import com.example.javafx.JavaFxSpringApp;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFxApp extends Application {

    private ConfigurableApplicationContext applicationContext;

    private static final Logger logger = LoggerFactory.getLogger(JavaFxApp.class);

    @Override
    public void init() {
        logger.info("** Before starting Spring Framework ** ");
        applicationContext = new SpringApplicationBuilder(JavaFxSpringApp.class).run();
        logger.info("** Spring Framework starts running ** ");
    }

    @Override
    public void start(Stage stage) {
        logger.info("** Stage Start ** ");
        applicationContext.publishEvent(new StageReadyEvent(stage));
        logger.info("** Event is published ** ");
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }

    public static class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage stage) {
            super(stage);
        }

        public Stage getStage() {
            return (Stage) getSource();
        }
    }
}

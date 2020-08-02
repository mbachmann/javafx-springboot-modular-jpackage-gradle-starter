package com.example.javafx.ui;

import com.example.javafx.JavaFxSpringApp;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.URL;

public class JavaFxApp extends Application {

    private ConfigurableApplicationContext applicationContext;

    private static final Logger logger = LoggerFactory.getLogger(JavaFxApp.class);

    @Override
    public void init() {

    }

    @Override
    public void start(Stage mainStage) {
        logger.info("** Before starting Spring Framework ** ");
        Stage splashStage = showSplash();
        initSpringContext(mainStage, splashStage);
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

    private Stage showSplash() {

        final int SPLASH_WIDTH = 720;

        Label labelTitle = new Label("Spring-Boot-Java-Fx");
        labelTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        labelTitle.setTextFill(Color.WHITE);
        labelTitle.setEffect(new DropShadow(2.0, Color.BLACK));

        Label labelText = new Label("Java-Fx Spring-Boot Modular JPackage Gradle Starter");
        labelText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        labelText.setTextFill(Color.WHITE);
        labelText.setEffect(new DropShadow(2.0, Color.BLACK));

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.BOTTOM_LEFT);
        vbox.getChildren().addAll(labelTitle, labelText);
        vbox.setSpacing(18);

        Image iconImage = getImage("icon_80x80-no-shadow.png");
        ImageView imageView = new ImageView(iconImage);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.BOTTOM_LEFT);
        hbox.getChildren().addAll(imageView, vbox);
        hbox.setPadding(new Insets(10, 50, 50, 50));
        hbox.setSpacing(50);

        Stage splashStage = new Stage();
        hbox.setBackground(new Background(getBackgroundImage(getImage("splash-image.jpg"))));
        splashStage.setScene(new Scene(hbox, SPLASH_WIDTH, 336));

        splashStage.centerOnScreen();
        splashStage.initStyle(StageStyle.UNDECORATED);
        splashStage.setTitle("Bal Bla");
        splashStage.show();
        return splashStage;
    }

    private void initSpringContext(Stage mainStage, Stage splashStage) {
        new Thread(() -> {
            logger.info("***********************************************");
            logger.info("*            Load Spring Context               *");
            logger.info("***********************************************");
            applicationContext = new SpringApplicationBuilder(JavaFxSpringApp.class).run();
            applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
            logger.info("***********************************************");
            logger.info("*    The Spring Framework is initialized       *");
            logger.info("***********************************************");
            Platform.runLater(() -> {
                applicationContext.publishEvent(new StageReadyEvent(mainStage));
                logger.info("** Event is published ** ");
                splashStage.hide();
                splashStage.close();
            });
        }).start();;
    }

    private Image getImage(String imageName) {
        URL url = getClass().getResource(imageName);
        return new Image(url.toExternalForm());
    }

    private BackgroundImage getBackgroundImage(Image image) {
        return new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
    }
}

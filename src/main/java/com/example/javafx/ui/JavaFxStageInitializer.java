package com.example.javafx.ui;

import com.example.javafx.domain.Project;
import com.example.javafx.service.ProjectService;
import com.example.javafx.service.TestService;
import com.example.javafx.ui.JavaFxApp.StageReadyEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.List;

@Component
public class JavaFxStageInitializer implements ApplicationListener<StageReadyEvent> {

    private static final Logger logger = LoggerFactory.getLogger(JavaFxApp.class);

    ProjectService projectService;
    ProjectsView projectsView;
    TestService testService;

    @Autowired
    public JavaFxStageInitializer (ProjectService projectService,
                                   ProjectsView projectsView,
                                   TestService testService) {

        this.projectService = projectService;
        this.projectsView = projectsView;
        this.testService = testService;

    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {

        logger.info("** Java App Read CSS ** " + testService.testMethod());

        Stage stage = event.getStage();
        stage.setScene(initLocalScene());
        stage.show();
    }

    private Scene initLocalScene() {
        List<Project> projects = projectService.findAllProjects();

        StringBuilder stringBuilder = new StringBuilder();
        projects.forEach((p) -> {
            logger.info(p.toString());
            stringBuilder.append(p.toString() + "\n");
        });

        StackPane rootPane = new StackPane();
        rootPane.setPrefSize(500, 500);
        Button button = new Button("Hello JavaFX with Spring Boot!");
        Label text = new Label(stringBuilder.toString());

        VBox vbox = new VBox(button,text);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10, 50, 50, 50));

        rootPane.getChildren().add(vbox);


        Scene scene = new Scene(rootPane);
        URL url = getClass().getResource("styles.css");
        scene.getStylesheets().add(url.toExternalForm());
        return scene;
    }
}

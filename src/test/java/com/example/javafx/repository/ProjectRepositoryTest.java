package com.example.javafx.repository;

import com.example.javafx.JavaFxSpringApp;
import com.example.javafx.domain.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@AutoConfigureTestDatabase
@SpringBootTest(classes= JavaFxSpringApp.class)
@Transactional
public class ProjectRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(ProjectRepositoryTest.class);

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void loadAllProjectsEagerly() {

        List<Project> allProjects = projectRepository.findAllAndFetchTasksEagerly();
        for (Project project : allProjects) {
            projectRepository.findOneAndFetchTasksEagerly(project.getId()).ifPresent(
                    p -> {
                        log.info("**** Tasks size of Project" + p.getName() + ": " + p.getTasks().size());
                        assert(p.getTasks().size() == project.getTasks().size());
                    });
        }
    }

}

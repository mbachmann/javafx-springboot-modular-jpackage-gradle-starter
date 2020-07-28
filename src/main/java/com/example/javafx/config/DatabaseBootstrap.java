package com.example.javafx.config;


import com.example.javafx.domain.Project;
import com.example.javafx.domain.Task;
import com.example.javafx.repository.ProjectRepository;
import com.example.javafx.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * manually fill-in data to DataBase
 */

public class DatabaseBootstrap implements InitializingBean {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TaskRepository taskRepository;

    private static final Logger log = LoggerFactory.getLogger(DatabaseBootstrap.class);

    @Override
    @Transactional
    public void afterPropertiesSet() throws Exception {

        List<Project> prjs = projectRepository.findByName("Bootstrap Project");
        if (prjs.size() == 0) {
            Project project = new Project();
            project.setName("Bootstrap Project");
            project.setDescription("Description Project Bootstrap");
            projectRepository.save(project);
            log.info("created: " + project.toString() );

            Task task = new Task();
            task.setName("Bootstrap Project Task");
            task.setDescription("Bootstrap Description");
            task.setProject(project);
            task.setStatus(Task.Status.COMPLETED);
            taskRepository.save(task);

            log.info("created: " + task.toString() );
            projectRepository.flush();
        }
        log.info("Bootstrap finished");

    }


}

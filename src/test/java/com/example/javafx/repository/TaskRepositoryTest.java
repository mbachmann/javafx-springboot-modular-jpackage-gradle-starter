package com.example.javafx.repository;

import com.example.javafx.JavaFxSpringApp;
import com.example.javafx.domain.Project;
import com.example.javafx.domain.Task;
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

import static org.springframework.test.util.AssertionErrors.fail;


@SpringBootTest(classes = JavaFxSpringApp.class)
@AutoConfigureTestDatabase
@Transactional
public class TaskRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(TaskRepositoryTest.class);

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void loadAllTasks() {
        List<Task> allTasks = taskRepository.findAll();
        assert(allTasks.size() > 0);

        verifyTasks(allTasks);
    }

    @Test
    public void loadTasksByProjectId() {
        List<Project> allProjects = projectRepository.findAllAndFetchTasksEagerly();

        for (Project project : allProjects) {
            log.info("********* Project Name: " + project.getName());
            List<Task> tasks = taskRepository.findByProjectId(project.getId());

            verifyTasks(tasks);
            verifyTasksWithName(tasks, project);
        }
    }

    @Test
    public void loadTasksByProjectName() {

        List<Project> allProjects = projectRepository.findAllAndFetchTasksEagerly();

        for (Project project : allProjects) {
            log.info("********* Project Name: " + project.getName());
            List<Task> tasks = taskRepository.findByProjectName(project.getName());

            verifyTasks(tasks);
            verifyTasksWithName(tasks, project);
        }
    }

    @Test
    public void loadTasksByProject() {

        List<Project> allProjects = projectRepository.findAllAndFetchTasksEagerly();

        for (Project project : allProjects) {
            log.info("********* Project Name: " + project.getName());
            List<Task> tasks = taskRepository.findByProject(project);

            verifyTasks(tasks);
            verifyTasksWithName(tasks, project);
        }
    }


    private void verifyTasks(List<Task> tasks) {
        for (Task task : tasks) {
            assert (task.getProject() != null);
            if (task.getProject() != null) {
                assert(task.getProject().getId() > 0);
            }
        }
    }

    @Test
    public void addTaskToProject() throws InterruptedException {
        Project project = new Project();
        project.setName("Test Project");
        project.setDescription("Description Test Project");
        Project newProject = projectRepository.save(project);
        Long id = project.getId();
        log.info("created: " + project.toString() );

        projectRepository.flush();

        Task task = new Task();
        task.setName("Test Project Task");
        task.setDescription("Test Project Task Description");
        task.setProject(project);
        task.setStatus(Task.Status.COMPLETED);
        taskRepository.save(task);
        Long taskId = task.getId();
        taskRepository.flush();

        em.refresh(newProject);

        projectRepository.findOneAndFetchTasksEagerly(id)
                .ifPresentOrElse(p -> {
                    log.info("loaded: " + p.toString() );
                    assert(p.getId().equals(id));
                    assert(p.getTasks().get(0).getId().equals(taskId));
                    List<Task> tasks = taskRepository.findByProject(project);
                    assert(tasks.get(0).getId().equals(taskId));

                },() -> fail("Created project could not be loaded!"));

    }

    private void verifyTasksWithName(List<Task> tasks, Project project) {
        for (Task task : tasks) {
            assert (task.getProject() != null);
            if (task.getProject() != null) {
                assert (task.getProject() != null);
                assert (task.getProject().getId().equals(project.getId()));
                assert (task.getProject().getName().equals(project.getName()));
                log.info("********* Project Name from Task with id " + task.getId() + ": " + project.getName());
            }
        }
    }

}

